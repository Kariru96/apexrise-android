package com.apexrise.offline

import android.app.Application
import androidx.room.Room
import com.apexrise.offline.data.ApexRiseDatabase

class ApexRiseApplication : Application() {
    val database: ApexRiseDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            ApexRiseDatabase::class.java,
            "apexrise_offline.db"
        ).build()
    }
}

