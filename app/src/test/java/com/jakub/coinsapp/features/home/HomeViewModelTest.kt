package com.jakub.coinsapp.features.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jakub.coinsapp.util.domainCoinWithDetails
import com.jakub.coinsapp.util.domainCoinsListMock
import com.jakub.domain.repositories.CoinsRepository
import com.jakub.domain.shared.ErrorEntity
import com.jakub.domain.shared.ResultResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var repository: CoinsRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getCoins_whenResponseSuccess_thenUpdateUiStateWithCoinsList() = runTest {
        val sut = HomeViewModel(repository)
        val testResults = mutableListOf<HomeUiState>()
        coEvery { repository.getCoins() } returns ResultResponse.Success(domainCoinsListMock)

        val job = launch(UnconfinedTestDispatcher(testScheduler)) {
            sut.uiState.toList(testResults)
        }
        sut.getCoins()

        assertEquals(3, testResults.size)
        assertFalse(testResults.first().isLoading)
        assertTrue(testResults[1].isLoading)
        assertEquals(3, testResults.last().coins.size)
        assertFalse(testResults.last().isLoading)
        job.cancel()
    }

    @Test
    fun getCoins_whenGenericError_thenUpdateUiStateWithErrorData() = runTest {
        val sut = HomeViewModel(repository)
        val testResults = mutableListOf<HomeUiState>()
        coEvery { repository.getCoins() } returns ResultResponse.Error(ErrorEntity.Generic())

        val job = launch(UnconfinedTestDispatcher(testScheduler)) {
            sut.uiState.toList(testResults)
        }
        sut.getCoins()

        assertEquals(3, testResults.size)
        assertFalse(testResults.first().isLoading)
        assertTrue(testResults[1].isLoading)
        assertTrue(testResults.last().hasError)
        assertFalse(testResults.last().errorMsg.isBlank())
        job.cancel()
    }

    @Test
    fun getCoins_whenUnknownError_thenUpdateUiStateWithErrorData() = runTest {
        val sut = HomeViewModel(repository)
        val testResults = mutableListOf<HomeUiState>()
        coEvery { repository.getCoins() } returns ResultResponse.Error(ErrorEntity.Unknown())

        val job = launch(UnconfinedTestDispatcher(testScheduler)) {
            sut.uiState.toList(testResults)
        }
        sut.getCoins()

        assertEquals(3, testResults.size)
        assertFalse(testResults.first().isLoading)
        assertTrue(testResults[1].isLoading)
        assertTrue(testResults.last().hasError)
        assertFalse(testResults.last().errorMsg.isBlank())
        job.cancel()
    }

    @Test
    fun getCoins_whenNetworkError_thenUpdateUiStateWithErrorData() = runTest {
        val sut = HomeViewModel(repository)
        val testResults = mutableListOf<HomeUiState>()
        coEvery { repository.getCoins() } returns ResultResponse.Error(ErrorEntity.NetworkFailure())

        val job = launch(UnconfinedTestDispatcher(testScheduler)) {
            sut.uiState.toList(testResults)
        }
        sut.getCoins()

        assertEquals(3, testResults.size)
        assertFalse(testResults.first().isLoading)
        assertTrue(testResults[1].isLoading)
        assertTrue(testResults.last().hasError)
        assertFalse(testResults.last().errorMsg.isBlank())
        job.cancel()
    }

    @Test
    fun filterCoins_whenFilterByCoin_thenUpdateUiStateCoinsList() = runTest {
        coEvery { repository.getCoins() } returns ResultResponse.Success(domainCoinsListMock)
        val sut = HomeViewModel(repository)
        val testResults = mutableListOf<HomeUiState>()

        val job = launch(UnconfinedTestDispatcher(testScheduler)) {
            sut.uiState.onEach { testResults.add(it) }.onCompletion {
                assertEquals(2, testResults.size) // init state + filtered state
                assertFalse(testResults.last().isLoading)
                assertEquals(2, testResults.last().coins.size) // list size after filtering
                assertEquals(testResults.last().coins.first().type, FilterType.COIN.filter)
                assertEquals(testResults.last().coins.last().type, FilterType.COIN.filter)
            }
        }
        sut.filterCoins(FilterType.COIN)
        advanceUntilIdle()

        job.cancel()
    }

    @Test
    fun getCoinDetails_whenResponseSuccess_thenUpdateUiStateSelectedCoin() = runTest {
        coEvery { repository.getCoinDetails("") } returns ResultResponse.Success(domainCoinWithDetails)
        val sut = HomeViewModel(repository)
        val testResults = mutableListOf<HomeUiState>()

        val job = launch(UnconfinedTestDispatcher(testScheduler)) {
            sut.uiState.toList(testResults)
        }
        sut.getCoinDetails("")

        assertNotNull(testResults.last().selectedCoin)
        assertEquals("Bitcoin", testResults.last().selectedCoin?.name)
        assertEquals("SHA256", testResults.last().selectedCoin?.hashAlgorithm)

        job.cancel()
    }

    @Test
    fun getCoinDetails_whenError_thenUpdateUiStateGetDetailsError() = runTest {
        coEvery { repository.getCoinDetails("") } returns ResultResponse.Error(ErrorEntity.Generic())
        val sut = HomeViewModel(repository)
        val testResults = mutableListOf<HomeUiState>()

        val job = launch(UnconfinedTestDispatcher(testScheduler)) {
            sut.uiState.toList(testResults)
        }
        sut.getCoinDetails("")

        assertNull(testResults.last().selectedCoin)
        assertTrue(testResults.last().getDetailsError)

        job.cancel()
    }

    @Test
    fun dismissCoinDetailsDialog_whenDismiss_thenUpdateUiStateSelectedCoin() = runTest {
        coEvery { repository.getCoinDetails("") } returns ResultResponse.Success(domainCoinWithDetails)
        val sut = HomeViewModel(repository)
        val testResults = mutableListOf<HomeUiState>()

        val job = launch(UnconfinedTestDispatcher(testScheduler)) {
            sut.uiState.toList(testResults)
        }
        sut.getCoinDetails("")
        sut.dismissCoinDetailsDialog()

        assertNotNull(testResults[1].selectedCoin)
        assertNull(testResults.last().selectedCoin)
        assertFalse(testResults.last().getDetailsError)

        job.cancel()
    }
}
