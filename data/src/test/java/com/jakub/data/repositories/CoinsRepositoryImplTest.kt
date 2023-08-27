package com.jakub.data.repositories


import com.jakub.data.services.CoinsService
import com.jakub.data.util.ApiCoinsListMock
import com.jakub.data.util.domainCoinsListMock
import com.jakub.domain.shared.ErrorEntity
import com.jakub.domain.shared.ResultResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.lang.RuntimeException

class CoinsRepositoryImplTest {

    @RelaxedMockK
    private lateinit var coinsService: CoinsService

    private lateinit var sut: CoinsRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        sut = CoinsRepositoryImpl(coinsService)
    }

    @Test
    fun getCoins_whenResponseSuccess_thenReturnDomainCoinsList() = runTest {
        coEvery { coinsService.getCoins() } returns Response.success(ApiCoinsListMock)

        val outcome = sut.getCoins()

        assertThat(outcome, instanceOf(ResultResponse.Success::class.java))
        assertEquals((outcome as ResultResponse.Success).data, domainCoinsListMock)
    }

    @Test
    fun getCoins_whenException_thenReturnError() = runTest {
        coEvery { coinsService.getCoins() } throws RuntimeException("ERROR_MSG")

        val outcome = sut.getCoins()

        assertThat(outcome, instanceOf(ResultResponse.Error::class.java))
        assertThat((outcome as ResultResponse.Error).error, instanceOf(ErrorEntity.Unknown::class.java))
    }

    @Test
    fun getCoins_whenBodyIsNull_thenReturnError() = runTest {
        coEvery { coinsService.getCoins() } returns Response.success(null)

        val outcome = sut.getCoins()

        assertThat(outcome, instanceOf(ResultResponse.Error::class.java))
        assertThat((outcome as ResultResponse.Error).error, instanceOf(ErrorEntity.Unknown::class.java))
    }
}