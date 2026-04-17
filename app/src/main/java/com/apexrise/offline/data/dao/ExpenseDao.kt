package com.apexrise.offline.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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

    @Delete
    suspend fun delete(expense: ExpenseEntity)
}

