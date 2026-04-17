package com.apexrise.offline.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.apexrise.offline.data.ApexRiseDatabase
import com.apexrise.offline.data.entity.ExpenseEntity
import com.apexrise.offline.data.entity.WakulimaRateEntity
import com.apexrise.offline.data.entity.WakulimaSaleEntity
import com.apexrise.offline.data.model.WakulimaSalesTotalsRow
import com.apexrise.offline.util.Time
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

data class ProfitsUiState(
    val month: String = Time.currentMonthIso(),
    val rate: Double? = null,
    val salesTotals: WakulimaSalesTotalsRow = WakulimaSalesTotalsRow(0.0, 0.0, 0.0, 0.0),
    val sales: List<WakulimaSaleEntity> = emptyList(),
    val expensesList: List<ExpenseEntity> = emptyList(),
    val revenue: Double = 0.0,
    val expenses: Double = 0.0,
    val netProfit: Double = 0.0,
)

@OptIn(ExperimentalCoroutinesApi::class)
class ProfitsViewModel(private val db: ApexRiseDatabase) : ViewModel() {
    private val month = MutableStateFlow(Time.currentMonthIso())

    private val _uiState = mutableStateOf(ProfitsUiState())
    val uiState: State<ProfitsUiState> = _uiState

    init {
        viewModelScope.launch {
            month.flatMapLatest { m ->
                val (start, end) = Time.monthStartEnd(m)
                val rateFlow = db.wakulimaDao().observeRate(m)
                val salesTotalsFlow = db.wakulimaDao().observeTotalsInRange(start, end)
                val salesFlow = db.wakulimaDao().observeSalesInRange(start, end)
                val expensesFlow = db.expenseDao().observeInRange(start, end)
                val expensesSumFlow = db.expenseDao().observeSumInRange(start, end)
                combine(rateFlow, salesTotalsFlow, salesFlow, expensesFlow, expensesSumFlow) { rate, totals, sales, expensesList, expensesSum ->
                    val revenue = rate?.let { totals.litresTotal * it } ?: 0.0
                    val net = revenue - expensesSum
                    ProfitsUiState(
                        month = m,
                        rate = rate,
                        salesTotals = totals,
                        sales = sales,
                        expensesList = expensesList,
                        revenue = revenue,
                        expenses = expensesSum,
                        netProfit = net,
                    )
                }
            }.collect { _uiState.value = it }
        }
    }

    fun setMonth(value: String) {
        month.value = value.ifBlank { Time.currentMonthIso() }
    }

    fun saveRate(rate: WakulimaRateEntity) {
        viewModelScope.launch {
            db.wakulimaDao().upsertRate(rate)
        }
    }

    fun addSale(sale: WakulimaSaleEntity) {
        viewModelScope.launch {
            db.wakulimaDao().insertSale(sale)
        }
    }

    fun deleteSale(sale: WakulimaSaleEntity) {
        viewModelScope.launch {
            db.wakulimaDao().deleteSale(sale)
        }
    }

    fun addExpense(expense: ExpenseEntity) {
        viewModelScope.launch {
            db.expenseDao().insert(expense)
        }
    }

    fun deleteExpense(expense: ExpenseEntity) {
        viewModelScope.launch {
            db.expenseDao().delete(expense)
        }
    }

    class Factory(private val db: ApexRiseDatabase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProfitsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ProfitsViewModel(db) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
