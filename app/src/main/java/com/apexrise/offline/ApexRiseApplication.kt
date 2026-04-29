package com.apexrise.offline

import android.app.Application
import androidx.room.Room
import com.apexrise.offline.data.ApexRiseDatabase
import com.apexrise.offline.data.MIGRATION_1_2
import com.apexrise.offline.network.NetworkClient

class ApexRiseApplication : Application() {
    val database: ApexRiseDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            ApexRiseDatabase::class.java,
            "apexrise_offline.db"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        // Initialize network client with your backend URL
        // Update the BASE_URL to your actual backend server
        NetworkClient.initialize(
            context = this,
            baseUrl = "http://10.0.2.2:8080/api/" // Change to your actual backend URL
        )
    }
}

