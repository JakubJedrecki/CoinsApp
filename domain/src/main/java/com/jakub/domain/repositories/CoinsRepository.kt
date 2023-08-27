package com.jakub.domain.repositories

import com.jakub.domain.models.Coin
import com.jakub.domain.shared.ResultResponse

interface CoinsRepository {
    suspend fun getCoins(): ResultResponse<List<Coin>>
    suspend fun getCoinDetails(id: String): ResultResponse<Coin>
}