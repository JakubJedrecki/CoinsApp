package com.jakub.data.util

import com.jakub.data.dto.ApiCoin
import com.jakub.domain.models.Coin

val ApiCoinsListMock = listOf(
    ApiCoin(
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
    ),
    ApiCoin(
        id = "btc-bitcoin",
        name = "Bitcoin",
        symbol = "BTC",
        isActive = true,
        isNew = false,
        rank = 1,
        type = "coin",
        description = "",
        hashAlgorithm = "",
        platform = ""
    ),
    ApiCoin(
        id = "eth-ethereum",
        name = "Ethereum",
        symbol = "ETH",
        isActive = true,
        isNew = false,
        rank = 2,
        type = "coin",
        description = "",
        hashAlgorithm = "",
        platform = ""
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
        type = "coin",
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
        type = "coin",
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

val apiCoinWithDetails = ApiCoin(
    id = "btc-bitcoin",
    name = "Bitcoin",
    symbol = "BTC",
    isActive = true,
    isNew = false,
    rank = 1,
    type = "coin",
    description = "Bitcoin is a cryptocurrency and worldwide payment system.",
    hashAlgorithm = "SHA256",
    platform = "-"
)

val domainCoinWithDetails = Coin(
    id = "btc-bitcoin",
    name = "Bitcoin",
    symbol = "BTC",
    isActive = true,
    isNew = false,
    rank = 1,
    type = "coin",
    description = "Bitcoin is a cryptocurrency and worldwide payment system.",
    hashAlgorithm = "SHA256",
    platform = "-"
)