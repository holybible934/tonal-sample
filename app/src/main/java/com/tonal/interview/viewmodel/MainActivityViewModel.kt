package com.tonal.interview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tonal.interview.data.Movement
import com.tonal.interview.data.MovementRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val repo: MovementRepository
): ViewModel() {
    private var _movementList: List<Movement>? = null
        set(value) {
            field = value
            value.let { movementList.postValue(it) }
        }
    val movementList: MutableLiveData<List<Movement>> = MutableLiveData()

    fun loadMovementList() {
        viewModelScope.launch {
            with(Dispatchers.IO) {
                _movementList = repo.fetchMovements()
            }
        }
    }
}