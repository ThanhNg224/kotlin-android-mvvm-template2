package com.thanhng224.app.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.thanhng224.app.R
import com.thanhng224.app.presentation.ui.theme.Dimens
import com.thanhng224.app.presentation.ui.theme.AccentColor
import com.thanhng224.app.presentation.ui.theme.CardBackground
import com.thanhng224.app.presentation.ui.theme.GradientEnd
import com.thanhng224.app.presentation.ui.theme.GradientStart
import com.thanhng224.app.presentation.ui.theme.Primary
import com.thanhng224.app.presentation.ui.theme.PrimaryVariant
import com.thanhng224.app.presentation.ui.theme.Secondary
import com.thanhng224.app.presentation.ui.theme.SecondaryVariant

@Composable
fun FavoritesScreen() {
    val colors = listOf(
        Primary,
        PrimaryVariant,
        Secondary,
        SecondaryVariant,
        CardBackground,
        GradientStart,
        GradientEnd,
        AccentColor
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(bottom = Dimens.spaceHuge),
        contentPadding = PaddingValues(Dimens.spaceLarge),
        verticalArrangement = Arrangement.spacedBy(Dimens.spaceMedium)
    ) {
        items(20) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.listItemHeight)
                    .clip(MaterialTheme.shapes.large)
                    .background(colors[index % colors.size])
                    .padding(Dimens.spaceLarge),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.favorites_item_label, index + 1),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}
