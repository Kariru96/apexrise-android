package com.apexrise.offline.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.apexrise.offline.data.ApexRiseDatabase
import com.apexrise.offline.data.entity.CowEntity
import kotlinx.coroutines.launch

class CowsViewModel(private val db: ApexRiseDatabase) : ViewModel() {
    private val _cows = mutableStateOf<List<CowEntity>>(emptyList())
    val cows: State<List<CowEntity>> = _cows

    init {
        viewModelScope.launch {
            db.cowDao().observeAll().collect { _cows.value = it }
        }
    }

    fun addCow(cow: CowEntity) {
        viewModelScope.launch {
            db.cowDao().insert(cow)
        }
    }

    class Factory(private val db: ApexRiseDatabase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CowsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CowsViewModel(db) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

