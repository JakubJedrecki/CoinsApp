package com.jakub.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jakub.ui.R

@Composable
fun FiltersRow(
    modifier: Modifier = Modifier,
    coinsClick: () -> Unit,
    tokensClick: () -> Unit,
    clearClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = stringResource(R.string.txt_filter))
        Button(onClick = { coinsClick() }) {
            Text(text = stringResource(R.string.txt_coins))
        }
        Button(onClick = { tokensClick() }) {
            Text(text = stringResource(R.string.txt_tokens))
        }
        Button(
            onClick = { clearClick() },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text(text = stringResource(R.string.txt_clear))
        }
    }
}