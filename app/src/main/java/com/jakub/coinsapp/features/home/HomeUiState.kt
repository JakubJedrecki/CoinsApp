package com.jakub.coinsapp.features.home

import com.jakub.domain.models.Coin

data class HomeUiState(
    val coins: List<Coin> = listOf(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val errorMsg: String = ""
)