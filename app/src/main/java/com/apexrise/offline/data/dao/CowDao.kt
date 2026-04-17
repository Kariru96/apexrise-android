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

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(cow: CowEntity): Long

    @Update
    suspend fun update(cow: CowEntity)

    @Delete
    suspend fun delete(cow: CowEntity)
}

