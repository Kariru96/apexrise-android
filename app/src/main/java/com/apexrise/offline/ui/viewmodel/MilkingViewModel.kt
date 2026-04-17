package com.apexrise.offline.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.apexrise.offline.data.ApexRiseDatabase
import com.apexrise.offline.data.entity.CowEntity
import com.apexrise.offline.data.entity.MilkRecordEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class MilkingViewModel(private val db: ApexRiseDatabase) : ViewModel() {
    private val _cows = mutableStateOf<List<CowEntity>>(emptyList())
    val cows: State<List<CowEntity>> = _cows

    init {
        viewModelScope.launch {
            db.cowDao().observeAll().collect { _cows.value = it }
        }
    }

    fun observeRecord(cowId: Long?, date: String): Flow<MilkRecordEntity?> {
        if (cowId == null) return emptyFlow()
        return db.milkRecordDao().observeByCowAndDate(cowId, date)
    }

    fun saveMilkRecord(record: MilkRecordEntity) {
        viewModelScope.launch {
            db.milkRecordDao().upsert(record)
        }
    }

    class Factory(private val db: ApexRiseDatabase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MilkingViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MilkingViewModel(db) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
