package com.jakub.data.repositories

import com.jakub.data.mappers.mapToDomain
import com.jakub.data.services.CoinsService
import com.jakub.domain.models.Coin
import com.jakub.domain.repositories.CoinsRepository
import com.jakub.domain.shared.ErrorEntity
import com.jakub.domain.shared.ResultResponse
import javax.inject.Inject

class CoinsRepositoryImpl @Inject constructor(
    private val coinsService: CoinsService
): CoinsRepository {

    override suspend fun getCoins(): ResultResponse<List<Coin>> {
        return try {
            coinsService.getCoins().run {
                this.body()?.let { list ->
                    if (isSuccessful) {

                        val coins = mutableListOf<Coin>()
                        list.map { coin ->
                            coin.mapToDomain()
                        }.let { coins.addAll(it) }

                        return ResultResponse.Success(coins)

                    } else {
                        val error = this.errorBody()?.toString() ?: ""
                        ResultResponse.Error(ErrorEntity.Unknown(error))
                    }
                } ?: ResultResponse.Error(ErrorEntity.Unknown())
            }
        } catch (exception: Exception) {
            ResultResponse.Error(ErrorEntity.Unknown(exception.message ?: ""))
        }
    }
}