package com.apexrise.offline.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.apexrise.offline.data.entity.CowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CowDao {
    @Query("SELECT * FROM cows ORDER BY tag_number;")
    fun observeAll(): Flow<List<CowEntity>>

    @Query("SELECT * FROM cows WHERE id = :cowId LIMIT 1;")
    fun observeById(cowId: Long): Flow<CowEntity?>

    @Query("SELECT COUNT(*) FROM cows;")
    fun observeCount(): Flow<Int>

    @Query("SELECT * FROM cows WHERE id = :cowId LIMIT 1;")
    suspend fun getCowById(cowId: Long): CowEntity?

    @Query("SELECT * FROM cows WHERE timestamp > :lastSyncTime AND last_synced_at IS NULL ORDER BY timestamp DESC;")
    suspend fun getUnsyncedCows(lastSyncTime: Long): List<CowEntity>

    @Query("UPDATE cows SET last_synced_at = :syncTime WHERE timestamp <= :beforeTimestamp AND last_synced_at IS NULL;")
    suspend fun updateSyncedCows(beforeTimestamp: Long, syncTime: Long)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(cow: CowEntity): Long

    @Update
    suspend fun update(cow: CowEntity)

    @Delete
    suspend fun delete(cow: CowEntity)
}

