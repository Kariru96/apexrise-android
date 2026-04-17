package com.apexrise.offline.data.model

import androidx.room.ColumnInfo

data class WakulimaSalesTotalsRow(
    @ColumnInfo(name = "session_1_total")
    val session1Total: Double,
    @ColumnInfo(name = "session_2_total")
    val session2Total: Double,
    @ColumnInfo(name = "session_3_total")
    val session3Total: Double,
    @ColumnInfo(name = "litres_total")
    val litresTotal: Double,
)

