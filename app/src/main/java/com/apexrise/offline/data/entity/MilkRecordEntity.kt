package com.apexrise.offline.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "milk_records",
    foreignKeys = [
        ForeignKey(
            entity = CowEntity::class,
            parentColumns = ["id"],
            childColumns = ["cow_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["date"]),
        Index(value = ["cow_id", "date"], unique = true),
    ],
)
data class MilkRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "cow_id")
    val cowId: Long,
    val date: String,
    @ColumnInfo(name = "session_1")
    val session1: Double = 0.0,
    @ColumnInfo(name = "session_2")
    val session2: Double = 0.0,
    @ColumnInfo(name = "session_3")
    val session3: Double = 0.0,
    @ColumnInfo(name = "session_4")
    val session4: Double = 0.0,
    val notes: String? = null,
)

