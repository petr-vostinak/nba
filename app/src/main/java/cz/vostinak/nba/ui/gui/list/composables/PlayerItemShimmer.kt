package cz.vostinak.nba.ui.gui.list.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import cz.vostinak.core.ui.preview.Theme
import cz.vostinak.core.ui.preview.ThemePreviewProvider
import cz.vostinak.core.ui.shimmer.brandShimmerEffect
import cz.vostinak.core.ui.theme.NBATheme

/**
 * Player item card with shimmer effect.
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlayerItemShimmer() {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .brandShimmerEffect()
            )

            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(28.dp)
                        .brandShimmerEffect()
                )

                Box(
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .width(120.dp)
                        .height(22.dp)
                        .brandShimmerEffect()
                )
            }
        }
    }
}

@Preview
@Composable
private fun ShowPlayerItem(@PreviewParameter(ThemePreviewProvider ::class) theme: Theme) {
    NBATheme(
        darkTheme = theme.isDarkMode
    ) {
        PlayerItemShimmer()
    }
}