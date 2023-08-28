package com.jakub.ui.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jakub.ui.R

@Preview
@Composable
fun CoinCardPreview() {
    CoinCard(
        name = "Bitcoin",
        symbol = "BTC",
        rank = "1",
        type = "coin",
        onCardClick = {}
    )
}

@Composable
fun CoinCard(
    modifier: Modifier = Modifier,
    name: String,
    symbol: String,
    rank: String,
    type: String,
    onCardClick: () -> Unit
) {
    Card(modifier = modifier.clickable { onCardClick() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(12.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium
            )
            Row {
                Text(text = stringResource(id = R.string.txt_rank))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "#$rank")
            }
            Row {
                Text(text = stringResource(id = R.string.txt_symbol))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = symbol)
            }
            Row {
                Text(text = stringResource(id = R.string.txt_type))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = type)
            }
        }
    }
}
