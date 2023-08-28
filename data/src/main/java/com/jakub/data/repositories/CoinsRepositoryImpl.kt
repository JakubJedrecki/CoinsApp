package com.jakub.data.repositories

import com.jakub.data.mappers.mapToDomain
import com.jakub.data.services.CoinsService
import com.jakub.domain.models.Coin
import com.jakub.domain.repositories.CoinsRepository
import com.jakub.domain.shared.ErrorEntity
import com.jakub.domain.shared.ResultResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import javax.inject.Inject

class CoinsRepositoryImpl @Inject constructor(
    private val coinsService: CoinsService,
) : CoinsRepository {

    override suspend fun getCoins(): ResultResponse<List<Coin>> {
        return try {
            coinsService.getCoins().run {
                this.body()?.let { list ->
                    if (isSuccessful) {

                        val coins = mutableListOf<Coin>()
                        list.map { coin ->
                            coin.mapToDomain()
                        }.let { coins.addAll(it) }

                        withContext(Dispatchers.IO) {
                            coins.sortBy { it.name.trim() }
                        }
                        return ResultResponse.Success(coins)

                    } else {
                        val error = this.errorBody()?.toString() ?: ""
                        ResultResponse.Error(ErrorEntity.Unknown(error))
                    }
                } ?: ResultResponse.Error(ErrorEntity.Unknown())
            }
        } catch (exception: UnknownHostException) {
            exception.printStackTrace()
            ResultResponse.Error(ErrorEntity.NetworkFailure())
        }
        catch (exception: Exception) {
            exception.printStackTrace()
            ResultResponse.Error(ErrorEntity.Unknown(exception.message ?: ""))
        }
    }

    override suspend fun getCoinDetails(id: String): ResultResponse<Coin> {
        return try {
            coinsService.getCoinDetails(id).run {
                this.body()?.let { coin ->
                    if (isSuccessful) {
                        val domainCoin = coin.mapToDomain()
                        return ResultResponse.Success(domainCoin)
                    } else {
                        val error = this.errorBody()?.toString() ?: ""
                        ResultResponse.Error(ErrorEntity.Unknown(error))
                    }
                } ?: ResultResponse.Error(ErrorEntity.Unknown())
            }
        } catch (exception: UnknownHostException) {
            exception.printStackTrace()
            ResultResponse.Error(ErrorEntity.NetworkFailure())
        }
        catch (exception: Exception) {
            exception.printStackTrace()
            ResultResponse.Error(ErrorEntity.Unknown(exception.message ?: ""))
        }
    }
}