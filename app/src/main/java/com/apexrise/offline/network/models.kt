package com.apexrise.offline.network

import com.squareup.moshi.JsonClass
import java.time.LocalDate

// Auth Models
@JsonClass(generateAdapter = true)
data class LoginRequest(
    val email: String,
    val password: String
)

@JsonClass(generateAdapter = true)
data class LoginResponse(
    val token: String,
    val userId: String,
    val email: String
)

@JsonClass(generateAdapter = true)
data class RegisterRequest(
    val email: String,
    val password: String,
    val farmName: String
)

// Sync Models
@JsonClass(generateAdapter = true)
data class SyncRequest(
    val userId: String,
    val lastSyncTime: Long,
    val localChanges: LocalChanges
)

@JsonClass(generateAdapter = true)
data class LocalChanges(
    val newCows: List<CowSync> = emptyList(),
    val updatedCows: List<CowSync> = emptyList(),
    val deletedCowIds: List<String> = emptyList(),
    val newMilkRecords: List<MilkRecordSync> = emptyList(),
    val updatedMilkRecords: List<MilkRecordSync> = emptyList(),
    val newWakulimaSales: List<WakulimaSaleSync> = emptyList(),
    val updatedWakulimaSales: List<WakulimaSaleSync> = emptyList(),
    val newExpenses: List<ExpenseSync> = emptyList(),
    val updatedExpenses: List<ExpenseSync> = emptyList()
)

@JsonClass(generateAdapter = true)
data class SyncResponse(
    val success: Boolean,
    val message: String,
    val conflicts: List<ConflictItem> = emptyList(),
    val serverData: ServerData? = null,
    val nextSyncTime: Long
)

@JsonClass(generateAdapter = true)
data class ConflictItem(
    val entityType: String,
    val entityId: String,
    val localTimestamp: Long,
    val serverTimestamp: Long,
    val localData: Map<String, Any?>,
    val serverData: Map<String, Any?>
)

@JsonClass(generateAdapter = true)
data class ServerData(
    val cows: List<CowSync> = emptyList(),
    val milkRecords: List<MilkRecordSync> = emptyList(),
    val wakulimaSales: List<WakulimaSaleSync> = emptyList(),
    val expenses: List<ExpenseSync> = emptyList()
)

// Entity Sync Models
@JsonClass(generateAdapter = true)
data class CowSync(
    val id: String,
    val name: String,
    val tagNumber: String,
    val breed: String,
    val purchaseDate: Long,
    val purchaseCost: Double,
    val timestamp: Long
)

@JsonClass(generateAdapter = true)
data class MilkRecordSync(
    val id: String,
    val cowId: String,
    val date: Long,
    val morningSession: Double,
    val afternoonSession: Double,
    val eveningSession: Double,
    val nightSession: Double,
    val notes: String? = null,
    val timestamp: Long
)

@JsonClass(generateAdapter = true)
data class WakulimaSaleSync(
    val id: String,
    val date: Long,
    val session1: Double,
    val session2: Double,
    val session3: Double,
    val totalLitres: Double,
    val ratePerLitre: Double,
    val totalAmount: Double,
    val timestamp: Long
)

@JsonClass(generateAdapter = true)
data class ExpenseSync(
    val id: String,
    val date: Long,
    val category: String,
    val amount: Double,
    val description: String? = null,
    val timestamp: Long
)

// Conflict Resolution
@JsonClass(generateAdapter = true)
data class ConflictResolution(
    val conflictId: String,
    val resolution: String, // "local" or "server"
    val data: Map<String, Any?>? = null
)

@JsonClass(generateAdapter = true)
data class ResolutionResponse(
    val success: Boolean,
    val message: String
)
