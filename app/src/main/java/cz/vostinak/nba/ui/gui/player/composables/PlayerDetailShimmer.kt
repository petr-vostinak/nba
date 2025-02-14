package cz.vostinak.nba.ui.gui.player.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import cz.vostinak.nba.ui.gui.commons.InfoCellShimmer
import cz.vostinak.nba.ui.gui.commons.SectionHeaderShimmer
import cz.vostinak.core.ui.shimmer.brandShimmerEffect

/**
 * Player detail shimmer.
 * @param modifier Modifier
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlayerDetailShimmer(
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .brandShimmerEffect()
            )

            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .width(200.dp)
                        .brandShimmerEffect(),
                    text = "",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .width(120.dp)
                        .brandShimmerEffect(),
                    text = "",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.LightGray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        SectionHeaderShimmer()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            InfoCellShimmer(
                modifier = Modifier.weight(1f)
            )

            Spacer(
                modifier = Modifier.size(8.dp)
            )

            InfoCellShimmer(
                modifier = Modifier.weight(1f)
            )

        }

        SectionHeaderShimmer()

        InfoCellShimmer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                .brandShimmerEffect(),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        ) {
            InfoCellShimmer(
                modifier = Modifier.weight(1f)
            )

            Spacer(
                modifier = Modifier.size(8.dp)
            )

            InfoCellShimmer(
                modifier = Modifier.weight(1f)
            )

            Spacer(
                modifier = Modifier.size(8.dp)
            )

            InfoCellShimmer(
                modifier = Modifier.weight(1f)
            )
        }

        SectionHeaderShimmer()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            InfoCellShimmer(
                modifier = Modifier.weight(1f)
            )

            Spacer(
                modifier = Modifier.size(8.dp)
            )

            InfoCellShimmer(
                modifier = Modifier.weight(1f)
            )

            Spacer(
                modifier = Modifier.size(8.dp)
            )

            InfoCellShimmer(
                modifier = Modifier.weight(1f)
            )
        }
    }
}