package com.jakub.coinsapp.util

import com.jakub.domain.models.Coin


val domainCoinsListMock = listOf(
    Coin(
        id = "btc-bitcoin",
        name = "Bitcoin",
        symbol = "BTC",
        isActive = true,
        isNew = false,
        rank = 1,
        type = "token",
        description = "",
        hashAlgorithm = "",
        platform = ""
    ),
    Coin(
        id = "eth-ethereum",
        name = "Ethereum",
        symbol = "ETH",
        isActive = true,
        isNew = false,
        rank = 2,
        type = "token",
        description = "",
        hashAlgorithm = "",
        platform = ""
    ),
    Coin(
        id = "usdt-tether",
        name = "Tether",
        symbol = "USDT",
        isActive = true,
        isNew = false,
        rank = 3,
        type = "token",
        description = "",
        hashAlgorithm = "",
        platform = ""
    )
)