package com.jakub.data.services

import com.jakub.data.dto.ApiCoin
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinsService {
    @GET("/v1/coins")
    suspend fun getCoins(): Response<List<ApiCoin>>

    @GET("/v1/coins/{id}")
    suspend fun getCoinDetails(
        @Path("id") id: String
    ): Response<ApiCoin>
}