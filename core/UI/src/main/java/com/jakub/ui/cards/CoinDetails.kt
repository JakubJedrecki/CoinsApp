package com.jakub.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jakub.ui.R
import com.jakub.ui.theme.CoinsAppTheme

@Preview
@Composable
fun CoinDetailsPreview() {
    CoinsAppTheme {
        CoinDetailsCard(
            name = "Bitcoin",
            symbol = "BTC",
            rank = "1",
            type = "coin",
            hashAlgorithm = "sha-256",
            description = "Lorem ipsum dolor sit",
            platform = "some platform"
        )
    }
}

@Composable
fun CoinDetailsCard(
    modifier: Modifier = Modifier,
    name: String,
    symbol: String,
    rank: String,
    type: String,
    hashAlgorithm: String,
    description: String,
    platform: String,
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Column {
                        Row {
                            Text(text = "#$rank")
                            Text(text = "  •  ")
                            Text(text = symbol)
                        }
                        Text(text = name, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            modifier = Modifier
                                .background(color = MaterialTheme.colorScheme.inversePrimary)
                                .padding(horizontal = 4.dp),
                            text = type,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                    }
                }
            }

            Text(text = description, modifier = Modifier.padding(vertical = 12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.txt_hash_algorithm),
                    style = TextStyle(color = MaterialTheme.colorScheme.tertiary)
                )
                Text(text = hashAlgorithm, fontWeight = FontWeight.Bold)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.txt_platform),
                    style = TextStyle(color = MaterialTheme.colorScheme.tertiary)
                )
                Text(text = platform, fontWeight = FontWeight.Bold)
            }
        }
    }
}