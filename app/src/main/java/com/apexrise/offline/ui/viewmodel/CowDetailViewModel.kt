package com.apexrise.offline.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.apexrise.offline.data.ApexRiseDatabase
import com.apexrise.offline.data.entity.MilkRecordEntity
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

data class CowRecordRow(
    val date: String,
    val session1: Double,
    val session2: Double,
    val session3: Double,
    val session4: Double,
    val dailyTotal: Double,
)

data class CowDetailUiState(
    val title: String = "Cow",
    val totalMilkLitres: Double = 0.0,
    val dailyAverageLitres: Double = 0.0,
    val records: List<CowRecordRow> = emptyList(),
)

class CowDetailViewModel(
    private val db: ApexRiseDatabase,
    private val cowId: Long,
) : ViewModel() {
    private val _uiState = mutableStateOf(CowDetailUiState())
    val uiState: State<CowDetailUiState> = _uiState

    init {
        viewModelScope.launch {
            val cowFlow = db.cowDao().observeById(cowId)
            val recordsFlow = db.milkRecordDao().observeByCow(cowId)
            cowFlow.combine(recordsFlow) { cow, records ->
                val title = cow?.name ?: "Cow"
                val total = records.sumOf { it.session1 + it.session2 + it.session3 + it.session4 }
                val days = records.map { it.date }.distinct().size
                val avg = if (days > 0) total / days else 0.0
                val rows = records.map { it.toRow() }
                CowDetailUiState(
                    title = title,
                    totalMilkLitres = total,
                    dailyAverageLitres = avg,
                    records = rows,
                )
            }.collect { _uiState.value = it }
        }
    }

    class Factory(
        private val db: ApexRiseDatabase,
        private val cowId: Long,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CowDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CowDetailViewModel(db, cowId) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

private fun MilkRecordEntity.toRow(): CowRecordRow {
    val daily = session1 + session2 + session3 + session4
    return CowRecordRow(
        date = date,
        session1 = session1,
        session2 = session2,
        session3 = session3,
        session4 = session4,
        dailyTotal = daily,
    )
}
