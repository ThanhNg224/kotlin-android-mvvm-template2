package com.thanhng224.app.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.thanhng224.app.R
import com.thanhng224.app.core.util.UiState
import com.thanhng224.app.presentation.ui.theme.Dimens

/**
 * Renders loading/error/success branches for a UiState in one place.
 */
@Composable
fun <T> UiState<T>.Render(
    modifier: Modifier = Modifier,
    loading: @Composable () -> Unit = { DefaultLoading(modifier) },
    error: @Composable (Throwable) -> Unit = { DefaultError(modifier, it) },
    success: @Composable (T) -> Unit
) {
    when {
        isLoading -> loading()
        isError -> error(error ?: Throwable(stringResource(id = R.string.error_unknown)))
        isSuccess -> success(data!!)
    }
}

@Composable
private fun DefaultLoading(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = Dimens.cardElevation),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.padding(Dimens.spaceXLarge),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                Text(
                    text = stringResource(id = R.string.loading_default),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = Dimens.spaceMedium)
                )
            }
        }
    }
}

@Composable
private fun DefaultError(
    modifier: Modifier = Modifier,
    throwable: Throwable
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = Dimens.cardElevation),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.padding(Dimens.spaceXLarge),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.error_generic_title),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = throwable.message ?: stringResource(id = R.string.error_unknown),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = Dimens.spaceSmall)
                )
            }
        }
    }
}
