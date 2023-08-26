package com.jakub.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
    CoinCard(number = "1")
}

@Composable
fun CoinCard(
    modifier: Modifier = Modifier,
    number: String,
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(12.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Bitcoin",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium
            )
            Row {
                Text(text = stringResource(id = R.string.txt_rank))
                Text(text = number)
            }
            Row {
                Text(text = stringResource(id = R.string.txt_symbol))
                Text(text = "BTC")
            }
            Row {
                Text(text = stringResource(id = R.string.txt_type))
                Text(text = "coin")
            }
        }
    }
}
