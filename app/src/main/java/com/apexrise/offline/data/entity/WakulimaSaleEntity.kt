package com.apexrise.offline.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "milk_sales",
    indices = [
        Index(value = ["date"]),
    ],
)
data class WakulimaSaleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,
    @ColumnInfo(name = "session_1")
    val session1: Double = 0.0,
    @ColumnInfo(name = "session_2")
    val session2: Double = 0.0,
    @ColumnInfo(name = "session_3")
    val session3: Double = 0.0,
    val litres: Double = 0.0,
)

