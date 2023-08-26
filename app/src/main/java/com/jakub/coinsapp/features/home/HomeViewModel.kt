package com.jakub.coinsapp.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakub.domain.models.Coin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> get() = _uiState.asStateFlow()

    init {
        getCoins()
    }

    fun getCoins() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true
                )
            }
            _uiState.update { state ->
                state.copy(
                    coins = listOf( /* TODO get coins */),
                    isLoading = false
                )
            }
        }
    }
}