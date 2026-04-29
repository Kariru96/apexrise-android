package com.apexrise.offline.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.apexrise.offline.ApexRiseApplication
import com.apexrise.offline.data.repository.SyncRepository
import java.util.concurrent.TimeUnit

class SyncWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    
    override suspend fun doWork(): Result {
        return try {
            val database = (applicationContext as ApexRiseApplication).database
            val syncRepository = SyncRepository(applicationContext, database)
            
            val result = syncRepository.syncWithServer()
            
            when (result) {
                is com.apexrise.offline.data.repository.SyncResult.Success -> {
                    // Log sync success
                    android.util.Log.d("SyncWorker", "Sync successful. Conflicts: ${result.conflictCount}")
                    Result.success()
                }
                is com.apexrise.offline.data.repository.SyncResult.Error -> {
                    // Log sync error
                    android.util.Log.e("SyncWorker", "Sync failed: ${result.message}")
                    // Retry on error
                    Result.retry()
                }
            }
        } catch (e: Exception) {
            android.util.Log.e("SyncWorker", "Exception during sync", e)
            Result.retry()
        }
    }
    
    companion object {
        const val SYNC_WORK_TAG = "apex_rise_sync"
        const val SYNC_WORK_NAME = "apex_rise_periodic_sync"
        
        /**
         * Schedule periodic sync every 30 minutes
         */
        fun scheduleSyncWork(context: Context) {
            val syncWork = PeriodicWorkRequest.Builder(
                SyncWorker::class.java,
                30, // 30 minutes
                TimeUnit.MINUTES,
                15, // Flex interval of 15 minutes
                TimeUnit.MINUTES
            )
                .addTag(SYNC_WORK_TAG)
                .build()
            
            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                SYNC_WORK_NAME,
                androidx.work.ExistingPeriodicWorkPolicy.KEEP,
                syncWork
            )
        }
        
        /**
         * Cancel periodic sync
         */
        fun cancelSyncWork(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(SYNC_WORK_NAME)
        }
    }
}
