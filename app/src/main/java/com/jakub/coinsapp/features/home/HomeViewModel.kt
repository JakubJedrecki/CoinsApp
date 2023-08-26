package com.jakub.coinsapp.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakub.domain.models.Coin
import com.jakub.domain.repositories.CoinsRepository
import com.jakub.domain.shared.ResultResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coinsRepository: CoinsRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> get() = _uiState.asStateFlow()

    init {
        getCoins()
    }

    fun getCoins() {
        _uiState.update { state -> state.copy(isLoading = true) }

        viewModelScope.launch {
            when(val response = coinsRepository.getCoins()) {
                is ResultResponse.Success -> {
                    _uiState.update { state ->
                        state.copy(
                            coins = response.data,
                            isLoading = false
                        )
                    }
                }
                is ResultResponse.Error -> {

                }
            }
        }
    }
}