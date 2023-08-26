package com.jakub.data.services

import com.jakub.data.dto.ApiCoin
import retrofit2.Response
import retrofit2.http.GET

interface CoinsService {
    @GET("/v1/coins")
    suspend fun getCoins(): Response<List<ApiCoin>>
}