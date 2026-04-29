package com.apexrise.offline.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.apexrise.offline.data.dao.CowDao
import com.apexrise.offline.data.dao.ExpenseDao
import com.apexrise.offline.data.dao.MilkRecordDao
import com.apexrise.offline.data.dao.WakulimaDao
import com.apexrise.offline.data.entity.CowEntity
import com.apexrise.offline.data.entity.ExpenseEntity
import com.apexrise.offline.data.entity.MilkRecordEntity
import com.apexrise.offline.data.entity.WakulimaRateEntity
import com.apexrise.offline.data.entity.WakulimaSaleEntity

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        val currentTime = System.currentTimeMillis()
        
        // Add columns to cows table
        database.execSQL("ALTER TABLE cows ADD COLUMN timestamp INTEGER NOT NULL DEFAULT $currentTime")
        database.execSQL("ALTER TABLE cows ADD COLUMN last_synced_at INTEGER")
        
        // Add columns to milk_records table
        database.execSQL("ALTER TABLE milk_records ADD COLUMN timestamp INTEGER NOT NULL DEFAULT $currentTime")
        database.execSQL("ALTER TABLE milk_records ADD COLUMN last_synced_at INTEGER")
        
        // Add columns to milk_sales table
        database.execSQL("ALTER TABLE milk_sales ADD COLUMN timestamp INTEGER NOT NULL DEFAULT $currentTime")
        database.execSQL("ALTER TABLE milk_sales ADD COLUMN last_synced_at INTEGER")
        
        // Add columns to expenses table
        database.execSQL("ALTER TABLE expenses ADD COLUMN timestamp INTEGER NOT NULL DEFAULT $currentTime")
        database.execSQL("ALTER TABLE expenses ADD COLUMN last_synced_at INTEGER")
    }
}

@Database(
    entities = [
        CowEntity::class,
        MilkRecordEntity::class,
        WakulimaSaleEntity::class,
        WakulimaRateEntity::class,
        ExpenseEntity::class,
    ],
    version = 2,
    exportSchema = false,
)
abstract class ApexRiseDatabase : RoomDatabase() {
    abstract fun cowDao(): CowDao
    abstract fun milkRecordDao(): MilkRecordDao
    abstract fun wakulimaDao(): WakulimaDao
    abstract fun expenseDao(): ExpenseDao
}
