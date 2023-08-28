package com.jakub.data.mappers

import com.jakub.data.dto.ApiCoin
import com.jakub.domain.models.Coin

fun ApiCoin.mapToDomain(): Coin = Coin(
    id = id,
    name = name,
    symbol = symbol,
    rank = rank,
    isNew = isNew,
    isActive = isActive,
    type = type,
    hashAlgorithm = hashAlgorithm ?: "-",
    description = description ?: "-",
    platform = platform ?: "-"
)