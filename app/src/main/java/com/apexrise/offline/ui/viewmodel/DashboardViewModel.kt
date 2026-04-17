package com.apexrise.offline.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.apexrise.offline.data.ApexRiseDatabase
import com.apexrise.offline.util.Time
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

data class DashboardUiState(
    val cowsCount: Int = 0,
    val todayMilkLitres: Double = 0.0,
    val monthlyRevenue: Double = 0.0,
    val monthlyNetProfit: Double = 0.0,
)

class DashboardViewModel(private val db: ApexRiseDatabase) : ViewModel() {
    private val _uiState = mutableStateOf(DashboardUiState())
    val uiState: State<DashboardUiState> = _uiState

    init {
        val today = Time.todayIso()
        val month = Time.currentMonthIso()
        val (start, end) = Time.monthStartEnd(month)

        viewModelScope.launch {
            val cowsCountFlow = db.cowDao().observeCount()
            val todayMilkFlow = db.milkRecordDao().observeTotalForDate(today)
            val salesTotalsFlow = db.wakulimaDao().observeTotalsInRange(start, end)
            val rateFlow = db.wakulimaDao().observeRate(month)
            val expensesFlow = db.expenseDao().observeSumInRange(start, end)

            combine(cowsCountFlow, todayMilkFlow, salesTotalsFlow, rateFlow, expensesFlow) { cowsCount, todayMilk, salesTotals, rate, expenses ->
                val revenue = rate?.let { salesTotals.litresTotal * it } ?: 0.0
                val netProfit = revenue - expenses
                DashboardUiState(
                    cowsCount = cowsCount,
                    todayMilkLitres = todayMilk,
                    monthlyRevenue = revenue,
                    monthlyNetProfit = netProfit,
                )
            }.collect { _uiState.value = it }
        }
    }

    class Factory(private val db: ApexRiseDatabase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DashboardViewModel(db) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

