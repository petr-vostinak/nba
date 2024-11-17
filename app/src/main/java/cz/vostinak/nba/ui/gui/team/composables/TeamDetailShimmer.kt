package cz.vostinak.nba.ui.gui.team.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cz.vostinak.nba.ui.gui.commons.InfoCellShimmer
import cz.vostinak.nba.ui.gui.commons.SectionHeaderShimmer
import cz.vostinak.nba.ui.shimmer.brandShimmerEffect

/**
 * Shimmer effect for team detail screen.
 * @param modifier Modifier
 */
@Composable
fun TeamDetailShimmer(
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .background(Color.White)
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .brandShimmerEffect()
            )
        }


        SectionHeaderShimmer()

        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            InfoCellShimmer(
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            InfoCellShimmer(
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            InfoCellShimmer(
                modifier = Modifier.weight(1f)
            )
        }

        SectionHeaderShimmer()

        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            InfoCellShimmer(
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            InfoCellShimmer(
                modifier = Modifier.weight(1f)
            )
        }
    }
}