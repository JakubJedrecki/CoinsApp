package com.jakub.coinsapp.features.home

import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jakub.coinsapp.features.coinDetails.CoinDetailsDialog
import com.jakub.ui.cards.CoinCard
import com.jakub.ui.components.FiltersRow
import com.jakub.ui.errors.ErrorView
import com.jakub.ui.loading.SmallLoadingIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Coins") }
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            HomeContent(
                uiState = uiState,
                onRefresh = viewModel::getCoins,
                onCoinClick = viewModel::getCoinDetails,
                onDialogDismiss = viewModel::dismissCoinDetailsDialog,
                filterCoins = viewModel::filterCoins
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    uiState: HomeUiState,
    onRefresh: () -> Unit,
    onCoinClick: (id: String) -> Unit,
    onDialogDismiss: () -> Unit,
    filterCoins: (filter: FilterType) -> Unit,
) {
    val context = LocalContext.current
    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isLoading,
        onRefresh = { onRefresh() }
    )

    FiltersRow(
        coinsClick = { filterCoins(FilterType.COIN) },
        tokensClick = { filterCoins(FilterType.TOKEN) },
        clearClick = { filterCoins(FilterType.NONE) }
    )

    Box(
        modifier = Modifier
            .pullRefresh(state = pullRefreshState)
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(uiState.coins, key = { it.id }) { item ->
                CoinCard(
                    modifier = Modifier.animateItemPlacement(
                        animationSpec = tween(durationMillis = 600)
                    ),
                    name = item.name,
                    symbol = item.symbol,
                    rank = item.rank.toString(),
                    type = item.type,
                    onCardClick = { onCoinClick(item.id) }
                )
                Spacer(modifier = Modifier.height(4.dp))
                Divider()
            }
        }

        if (uiState.getCoinsError) {
            ErrorView(uiState.errorMsg)
        }

        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = uiState.isLoading,
            state = pullRefreshState,
        )

        if (uiState.selectedCoin != null) {
            CoinDetailsDialog(
                coin = uiState.selectedCoin,
                onDismiss = onDialogDismiss
            )
        }

        LaunchedEffect(key1 = uiState.getDetailsError) {
            if (uiState.getDetailsError) {
                Toast.makeText(context, "Couldn't retrieve coin details", Toast.LENGTH_LONG)
                    .show()
            }
        }

        if (uiState.isLoadingDetails) {
            SmallLoadingIndicator()
        }
    }
}





















