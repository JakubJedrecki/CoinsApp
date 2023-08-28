package com.jakub.data.mappers

import com.jakub.data.util.apiCoinWithDetails
import com.jakub.data.util.domainCoinWithDetails
import org.junit.Assert.assertEquals
import org.junit.Test

class CoinMapperKtTest {

    @Test
    fun apiCoin_whenMapToDomain_thenReturnCoin() {
        val apiCoin = apiCoinWithDetails

        val mappedCoin = apiCoin.mapToDomain()

        assertEquals(domainCoinWithDetails, mappedCoin)
    }
}