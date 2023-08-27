package com.jakub.coinsapp.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakub.domain.models.Coin
import com.jakub.domain.repositories.CoinsRepository
import com.jakub.domain.shared.ErrorEntity
import com.jakub.domain.shared.ResultResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coinsRepository: CoinsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> get() = _uiState.asStateFlow()

    private var coinsList: List<Coin> = emptyList()

    init {
        getCoins()
    }

    fun getCoins() {
        _uiState.update { state -> state.copy(isLoading = true) }

        viewModelScope.launch {
            when (val response = coinsRepository.getCoins()) {
                is ResultResponse.Success -> {
                    coinsList = response.data
                    _uiState.update { state ->
                        state.copy(
                            coins = coinsList,
                            hasError = false,
                            isLoading = false
                        )
                    }
                }

                is ResultResponse.Error -> {
                    when (response.error) {
                        is ErrorEntity.Generic,
                        is ErrorEntity.NetworkFailure,
                        is ErrorEntity.Unknown,
                        -> {
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    hasError = true,
                                    errorMsg = response.error.message
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun filterCoins(filterType: FilterType) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { state ->
                state.copy(
                    coins = if (filterType == FilterType.NONE) {
                        coinsList
                    } else {
                        coinsList.filter { coin ->
                            coin.type == filterType.filter
                        }
                    }
                )
            }
        }
    }
}