package com.apexrise.offline.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.apexrise.offline.data.entity.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query(
        """
        SELECT * FROM expenses
         WHERE date >= :startDate AND date < :endDate
         ORDER BY date DESC, id DESC;
        """
    )
    fun observeInRange(startDate: String, endDate: String): Flow<List<ExpenseEntity>>

    @Query("SELECT * FROM expenses WHERE id = :expenseId LIMIT 1;")
    suspend fun getExpenseById(expenseId: Long): ExpenseEntity?

    @Query("SELECT * FROM expenses WHERE timestamp > :lastSyncTime AND last_synced_at IS NULL ORDER BY timestamp DESC;")
    suspend fun getUnsyncedExpenses(lastSyncTime: Long): List<ExpenseEntity>

    @Query("UPDATE expenses SET last_synced_at = :syncTime WHERE timestamp <= :beforeTimestamp AND last_synced_at IS NULL;")
    suspend fun updateSyncedExpenses(beforeTimestamp: Long, syncTime: Long)

    @Query(
        """
        SELECT COALESCE(SUM(amount), 0.0)
          FROM expenses
         WHERE date >= :startDate AND date < :endDate;
        """
    )
    fun observeSumInRange(startDate: String, endDate: String): Flow<Double>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(expense: ExpenseEntity): Long

    @Update
    suspend fun update(expense: ExpenseEntity)

    @Delete
    suspend fun delete(expense: ExpenseEntity)
}

