package com.apexrise.offline.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.apexrise.offline.data.ApexRiseDatabase
import com.apexrise.offline.data.entity.CowEntity
import com.apexrise.offline.data.model.MilkRecordListRow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class RecordsViewModel(private val db: ApexRiseDatabase) : ViewModel() {
    private val _cows = mutableStateOf<List<CowEntity>>(emptyList())
    val cows: State<List<CowEntity>> = _cows

    private val cowFilter = MutableStateFlow<Long?>(null)
    private val _records = mutableStateOf<List<MilkRecordListRow>>(emptyList())
    val records: State<List<MilkRecordListRow>> = _records

    init {
        viewModelScope.launch {
            db.cowDao().observeAll().collect { _cows.value = it }
        }
        viewModelScope.launch {
            cowFilter.flatMapLatest { db.milkRecordDao().observeAllWithCow(it) }.collect { _records.value = it }
        }
    }

    fun setCowFilter(cowId: Long?) {
        cowFilter.value = cowId
    }

    fun deleteRecord(recordId: Long) {
        viewModelScope.launch {
            db.milkRecordDao().deleteById(recordId)
        }
    }

    class Factory(private val db: ApexRiseDatabase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RecordsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return RecordsViewModel(db) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
