package com.apexrise.offline.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wakulima_rates")
data class WakulimaRateEntity(
    @PrimaryKey
    val month: String,
    @ColumnInfo(name = "price_per_litre")
    val pricePerLitre: Double,
)

