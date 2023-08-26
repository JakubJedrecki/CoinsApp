package com.jakub.data.util

import com.jakub.data.dto.ApiCoin
import com.jakub.domain.models.Coin

val ApiCoinsListMock = listOf(
    ApiCoin(
        id = "btc-bitcoin",
        name = "Bitcoin",
        symbol = "BTC",
        isActive = true,
        isNew = false,
        rank = 1,
        type = "coin"
    ),
    ApiCoin(
        id = "eth-ethereum",
        name = "Ethereum",
        symbol = "ETH",
        isActive = true,
        isNew = false,
        rank = 2,
        type = "coin"
    ),
    ApiCoin(
        id = "usdt-tether",
        name = "Tether",
        symbol = "USDT",
        isActive = true,
        isNew = false,
        rank = 3,
        type = "token"
    )
)

val domainCoinsListMock = listOf(
    Coin(
        id = "btc-bitcoin",
        name = "Bitcoin",
        symbol = "BTC",
        isActive = true,
        isNew = false,
        rank = 1,
        type = "coin"
    ),
    Coin(
        id = "eth-ethereum",
        name = "Ethereum",
        symbol = "ETH",
        isActive = true,
        isNew = false,
        rank = 2,
        type = "coin"
    ),
    Coin(
        id = "usdt-tether",
        name = "Tether",
        symbol = "USDT",
        isActive = true,
        isNew = false,
        rank = 3,
        type = "token"
    )
)