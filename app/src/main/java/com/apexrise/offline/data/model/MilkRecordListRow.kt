package com.apexrise.offline.data.model

import androidx.room.ColumnInfo

data class MilkRecordListRow(
    val id: Long,
    val date: String,
    val cowId: Long,
    @ColumnInfo(name = "cow_name")
    val cowName: String,
    @ColumnInfo(name = "cow_tag_number")
    val cowTagNumber: String,
    @ColumnInfo(name = "session_1")
    val session1: Double,
    @ColumnInfo(name = "session_2")
    val session2: Double,
    @ColumnInfo(name = "session_3")
    val session3: Double,
    @ColumnInfo(name = "session_4")
    val session4: Double,
    @ColumnInfo(name = "daily_total")
    val dailyTotal: Double,
)

