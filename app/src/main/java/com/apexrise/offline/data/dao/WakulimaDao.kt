package com.apexrise.offline.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apexrise.offline.data.entity.WakulimaRateEntity
import com.apexrise.offline.data.entity.WakulimaSaleEntity
import com.apexrise.offline.data.model.WakulimaSalesTotalsRow
import kotlinx.coroutines.flow.Flow

@Dao
interface WakulimaDao {
    @Query(
        """
        SELECT * FROM milk_sales
         WHERE date >= :startDate AND date < :endDate
         ORDER BY date DESC, id DESC;
        """
    )
    fun observeSalesInRange(startDate: String, endDate: String): Flow<List<WakulimaSaleEntity>>

    @Query(
        """
        SELECT COALESCE(SUM(session_1), 0.0) AS session_1_total,
               COALESCE(SUM(session_2), 0.0) AS session_2_total,
               COALESCE(SUM(session_3), 0.0) AS session_3_total,
               COALESCE(SUM(litres), 0.0) AS litres_total
          FROM milk_sales
         WHERE date >= :startDate AND date < :endDate;
        """
    )
    fun observeTotalsInRange(startDate: String, endDate: String): Flow<WakulimaSalesTotalsRow>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertSale(sale: WakulimaSaleEntity): Long

    @Delete
    suspend fun deleteSale(sale: WakulimaSaleEntity)

    @Query("SELECT price_per_litre FROM wakulima_rates WHERE month = :month LIMIT 1;")
    fun observeRate(month: String): Flow<Double?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertRate(rate: WakulimaRateEntity)
}

