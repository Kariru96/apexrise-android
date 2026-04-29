package com.apexrise.offline.data.repository

import android.content.Context
import com.apexrise.offline.data.ApexRiseDatabase
import com.apexrise.offline.data.entity.CowEntity
import com.apexrise.offline.data.entity.ExpenseEntity
import com.apexrise.offline.data.entity.MilkRecordEntity
import com.apexrise.offline.data.entity.WakulimaSaleEntity
import com.apexrise.offline.network.ApexRiseApi
import com.apexrise.offline.network.ConflictItem
import com.apexrise.offline.network.CowSync
import com.apexrise.offline.network.ExpenseSync
import com.apexrise.offline.network.LocalChanges
import com.apexrise.offline.network.MilkRecordSync
import com.apexrise.offline.network.NetworkClient
import com.apexrise.offline.network.SyncRequest
import com.apexrise.offline.network.TokenManager
import com.apexrise.offline.network.WakulimaSaleSync
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SyncRepository(context: Context, private val database: ApexRiseDatabase) {
    private val api: ApexRiseApi = NetworkClient.getApi()
    private val tokenManager: TokenManager = NetworkClient.getTokenManager()

    /**
     * Sync all local changes with the server
     */
    suspend fun syncWithServer() = withContext(Dispatchers.IO) {
        try {
            val userId = tokenManager.getUserId() ?: throw Exception("Not logged in")
            val lastSyncTime = getLastSyncTime()

            // Gather all local changes
            val localChanges = getLocalChanges(lastSyncTime)

            // Create sync request
            val syncRequest = SyncRequest(
                userId = userId,
                lastSyncTime = lastSyncTime,
                localChanges = localChanges
            )

            // Send to server
            val response = api.syncData(
                token = "Bearer ${tokenManager.getToken()!!}",
                request = syncRequest
            )

            if (response.success) {
                // Update local database with server changes
                response.serverData?.let { serverData ->
                    updateLocalDatabase(serverData)
                }

                // Mark all synced records
                markSynced(lastSyncTime)

                // Update last sync time
                saveLastSyncTime(System.currentTimeMillis())

                SyncResult.Success(
                    conflictCount = response.conflicts.size,
                    conflicts = response.conflicts
                )
            } else {
                SyncResult.Error(response.message)
            }
        } catch (e: Exception) {
            SyncResult.Error(e.message ?: "Unknown error during sync")
        }
    }

    /**
     * Get all changes since last sync
     */
    private suspend fun getLocalChanges(lastSyncTime: Long): LocalChanges = withContext(Dispatchers.IO) {
        val cowsDao = database.cowDao()
        val milkRecordDao = database.milkRecordDao()
        val wakulimaDao = database.wakulimaDao()
        val expenseDao = database.expenseDao()

        return@withContext LocalChanges(
            newCows = cowsDao.getUnsyncedCows(lastSyncTime).map { it.toSync() },
            updatedCows = emptyList(), // Could add update tracking
            newMilkRecords = milkRecordDao.getUnsyncedRecords(lastSyncTime).map { it.toSync() },
            updatedMilkRecords = emptyList(),
            newWakulimaSales = wakulimaDao.getUnsyncedSales(lastSyncTime).map { it.toSync() },
            updatedWakulimaSales = emptyList(),
            newExpenses = expenseDao.getUnsyncedExpenses(lastSyncTime).map { it.toSync() },
            updatedExpenses = emptyList()
        )
    }

    /**
     * Update local database with server data
     */
    private suspend fun updateLocalDatabase(serverData: com.apexrise.offline.network.ServerData) = withContext(Dispatchers.IO) {
        val cowsDao = database.cowDao()
        val milkRecordDao = database.milkRecordDao()
        val wakulimaDao = database.wakulimaDao()
        val expenseDao = database.expenseDao()

        // Upsert cows
        serverData.cows.forEach { cowSync ->
            val existing = cowsDao.getCowById(cowSync.id.toLongOrNull() ?: 0)
            if (existing != null) {
                cowsDao.update(cowSync.toEntity(existing.id))
            } else {
                cowsDao.insert(cowSync.toEntity())
            }
        }

        // Upsert milk records
        serverData.milkRecords.forEach { recordSync ->
            val existing = milkRecordDao.getRecordById(recordSync.id.toLongOrNull() ?: 0)
            if (existing != null) {
                milkRecordDao.update(recordSync.toEntity(existing.id))
            }
        }

        // Upsert wakulima sales
        serverData.wakulimaSales.forEach { saleSync ->
            val existing = wakulimaDao.getSaleById(saleSync.id.toLongOrNull() ?: 0)
            if (existing != null) {
                wakulimaDao.updateSale(saleSync.toEntity(existing.id))
            }
        }

        // Upsert expenses
        serverData.expenses.forEach { expenseSync ->
            val existing = expenseDao.getExpenseById(expenseSync.id.toLongOrNull() ?: 0)
            if (existing != null) {
                expenseDao.update(expenseSync.toEntity(existing.id))
            }
        }
    }

    /**
     * Mark records as synced
     */
    private suspend fun markSynced(beforeTimestamp: Long) = withContext(Dispatchers.IO) {
        val currentTime = System.currentTimeMillis()
        val cowsDao = database.cowDao()
        val milkRecordDao = database.milkRecordDao()
        val wakulimaDao = database.wakulimaDao()
        val expenseDao = database.expenseDao()

        // Update sync timestamps for all records modified before this sync
        cowsDao.updateSyncedCows(beforeTimestamp, currentTime)
        milkRecordDao.updateSyncedRecords(beforeTimestamp, currentTime)
        wakulimaDao.updateSyncedSales(beforeTimestamp, currentTime)
        expenseDao.updateSyncedExpenses(beforeTimestamp, currentTime)
    }

    /**
     * Get last successful sync time
     */
    private suspend fun getLastSyncTime(): Long = withContext(Dispatchers.IO) {
        // Could store in DataStore or SharedPreferences
        0L // Start from epoch for initial sync
    }

    /**
     * Save last sync time
     */
    private suspend fun saveLastSyncTime(time: Long) = withContext(Dispatchers.IO) {
        // Store in DataStore or SharedPreferences
    }
}

// Extension functions for conversion
private fun CowEntity.toSync() = CowSync(
    id = this.id.toString(),
    name = this.name,
    tagNumber = this.tagNumber,
    breed = this.breed ?: "",
    purchaseDate = this.purchaseDate?.toLongOrNull() ?: 0L,
    purchaseCost = this.purchaseCost ?: 0.0,
    timestamp = this.timestamp
)

private fun MilkRecordEntity.toSync() = MilkRecordSync(
    id = this.id.toString(),
    cowId = this.cowId.toString(),
    date = this.date.toLongOrNull() ?: 0L,
    morningSession = this.session1,
    afternoonSession = this.session2,
    eveningSession = this.session3,
    nightSession = this.session4,
    notes = this.notes,
    timestamp = this.timestamp
)

private fun WakulimaSaleEntity.toSync() = WakulimaSaleSync(
    id = this.id.toString(),
    date = this.date.toLongOrNull() ?: 0L,
    session1 = this.session1,
    session2 = this.session2,
    session3 = this.session3,
    totalLitres = this.litres,
    ratePerLitre = 0.0, // Get from WakulimaRate if needed
    totalAmount = 0.0,
    timestamp = this.timestamp
)

private fun ExpenseEntity.toSync() = ExpenseSync(
    id = this.id.toString(),
    date = this.date.toLongOrNull() ?: 0L,
    category = this.category,
    amount = this.amount,
    description = this.description,
    timestamp = this.timestamp
)

private fun CowSync.toEntity(id: Long = 0) = CowEntity(
    id = id,
    name = this.name,
    tagNumber = this.tagNumber,
    breed = this.breed,
    purchaseDate = this.purchaseDate.toString(),
    purchaseCost = this.purchaseCost,
    timestamp = this.timestamp,
    lastSyncedAt = System.currentTimeMillis()
)

private fun MilkRecordSync.toEntity(id: Long = 0) = MilkRecordEntity(
    id = id,
    cowId = this.cowId.toLongOrNull() ?: 0,
    date = this.date.toString(),
    session1 = this.morningSession,
    session2 = this.afternoonSession,
    session3 = this.eveningSession,
    session4 = this.nightSession,
    notes = this.notes,
    timestamp = this.timestamp,
    lastSyncedAt = System.currentTimeMillis()
)

private fun WakulimaSaleSync.toEntity(id: Long = 0) = WakulimaSaleEntity(
    id = id,
    date = this.date.toString(),
    session1 = this.session1,
    session2 = this.session2,
    session3 = this.session3,
    litres = this.totalLitres,
    timestamp = this.timestamp,
    lastSyncedAt = System.currentTimeMillis()
)

private fun ExpenseSync.toEntity(id: Long = 0) = ExpenseEntity(
    id = id,
    date = this.date.toString(),
    category = this.category,
    amount = this.amount,
    description = this.description,
    timestamp = this.timestamp,
    lastSyncedAt = System.currentTimeMillis()
)

sealed class SyncResult {
    data class Success(val conflictCount: Int, val conflicts: List<ConflictItem> = emptyList()) : SyncResult()
    data class Error(val message: String) : SyncResult()
}
