package com.apexrise.offline.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "cows",
    indices = [
        Index(value = ["tag_number"], unique = true),
    ],
)
data class CowEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    @ColumnInfo(name = "tag_number")
    val tagNumber: String,
    val breed: String? = null,
    @ColumnInfo(name = "purchase_date")
    val purchaseDate: String? = null,
    @ColumnInfo(name = "purchase_cost")
    val purchaseCost: Double? = null,
)

