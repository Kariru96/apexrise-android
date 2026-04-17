package com.apexrise.offline.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apexrise.offline.data.entity.MilkRecordEntity
import com.apexrise.offline.data.model.MilkRecordListRow
import kotlinx.coroutines.flow.Flow

@Dao
interface MilkRecordDao {
    @Query("SELECT * FROM milk_records WHERE cow_id = :cowId ORDER BY date DESC, id DESC;")
    fun observeByCow(cowId: Long): Flow<List<MilkRecordEntity>>

    @Query("SELECT * FROM milk_records WHERE cow_id = :cowId AND date = :date LIMIT 1;")
    fun observeByCowAndDate(cowId: Long, date: String): Flow<MilkRecordEntity?>

    @Query(
        """
        SELECT COALESCE(SUM(session_1 + session_2 + session_3 + session_4), 0.0)
          FROM milk_records
         WHERE date = :date;
        """
    )
    fun observeTotalForDate(date: String): Flow<Double>

    @Query(
        """
        SELECT r.id,
               r.date,
               r.cow_id AS cowId,
               c.name AS cow_name,
               c.tag_number AS cow_tag_number,
               r.session_1, r.session_2, r.session_3, r.session_4,
               (r.session_1 + r.session_2 + r.session_3 + r.session_4) AS daily_total
          FROM milk_records r
          JOIN cows c ON c.id = r.cow_id
         WHERE (:cowId IS NULL OR r.cow_id = :cowId)
         ORDER BY r.date DESC, r.id DESC;
        """
    )
    fun observeAllWithCow(cowId: Long?): Flow<List<MilkRecordListRow>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(record: MilkRecordEntity): Long

    @Delete
    suspend fun delete(record: MilkRecordEntity)

    @Query("DELETE FROM milk_records WHERE id = :recordId;")
    suspend fun deleteById(recordId: Long)
}
