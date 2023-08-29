package com.jakub.coinsapp.features.coinDetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import com.jakub.domain.models.Coin
import com.jakub.ui.cards.CoinDetailsCard

@Composable
fun CoinDetailsDialog(
    onDismiss: () -> Unit,
    coin: Coin
) {
    Dialog(onDismissRequest = onDismiss) {
        CoinDetailsCard(
            name = coin.name,
            symbol = coin.symbol,
            rank = coin.rank.toString(),
            type = coin.type,
            hashAlgorithm = coin.hashAlgorithm,
            description = coin.description,
            platform = coin.platform
        )
    }
}