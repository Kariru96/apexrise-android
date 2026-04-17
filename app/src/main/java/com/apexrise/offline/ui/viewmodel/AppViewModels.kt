package com.apexrise.offline.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apexrise.offline.ApexRiseApplication
import com.apexrise.offline.data.ApexRiseDatabase

object AppViewModels {
    @Composable
    private fun db(): ApexRiseDatabase {
        val app = LocalContext.current.applicationContext as ApexRiseApplication
        return app.database
    }

    @Composable
    fun dashboard(): DashboardViewModel {
        val db = db()
        return viewModel(factory = remember { DashboardViewModel.Factory(db) })
    }

    @Composable
    fun cows(): CowsViewModel {
        val db = db()
        return viewModel(factory = remember { CowsViewModel.Factory(db) })
    }

    @Composable
    fun cowDetail(cowId: Long): CowDetailViewModel {
        val db = db()
        return viewModel(factory = remember { CowDetailViewModel.Factory(db, cowId) })
    }

    @Composable
    fun milking(): MilkingViewModel {
        val db = db()
        return viewModel(factory = remember { MilkingViewModel.Factory(db) })
    }

    @Composable
    fun records(): RecordsViewModel {
        val db = db()
        return viewModel(factory = remember { RecordsViewModel.Factory(db) })
    }

    @Composable
    fun profits(): ProfitsViewModel {
        val db = db()
        return viewModel(factory = remember { ProfitsViewModel.Factory(db) })
    }
}

