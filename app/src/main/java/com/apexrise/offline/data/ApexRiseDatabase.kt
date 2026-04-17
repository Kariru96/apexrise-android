package com.apexrise.offline.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.apexrise.offline.data.dao.CowDao
import com.apexrise.offline.data.dao.ExpenseDao
import com.apexrise.offline.data.dao.MilkRecordDao
import com.apexrise.offline.data.dao.WakulimaDao
import com.apexrise.offline.data.entity.CowEntity
import com.apexrise.offline.data.entity.ExpenseEntity
import com.apexrise.offline.data.entity.MilkRecordEntity
import com.apexrise.offline.data.entity.WakulimaRateEntity
import com.apexrise.offline.data.entity.WakulimaSaleEntity

@Database(
    entities = [
        CowEntity::class,
        MilkRecordEntity::class,
        WakulimaSaleEntity::class,
        WakulimaRateEntity::class,
        ExpenseEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class ApexRiseDatabase : RoomDatabase() {
    abstract fun cowDao(): CowDao
    abstract fun milkRecordDao(): MilkRecordDao
    abstract fun wakulimaDao(): WakulimaDao
    abstract fun expenseDao(): ExpenseDao
}
