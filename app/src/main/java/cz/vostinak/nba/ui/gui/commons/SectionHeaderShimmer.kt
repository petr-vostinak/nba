package cz.vostinak.nba.ui.gui.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import cz.vostinak.nba.ui.preview.Theme
import cz.vostinak.nba.ui.preview.ThemePreviewProvider
import cz.vostinak.nba.ui.shimmer.brandShimmerEffect
import cz.vostinak.nba.ui.theme.NBATheme

/**
 * Player info section title shimmer.
 */
@Composable
fun SectionHeaderShimmer() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .brandShimmerEffect(),
        text = "",
        style = MaterialTheme.typography.titleSmall,
        color = Color.White
    )
}

@Preview
@Composable
private fun ShowSectionHeaderShimmer(@PreviewParameter(ThemePreviewProvider ::class) theme: Theme) {
    NBATheme(
        darkTheme = theme.isDarkMode
    ) {
        SectionHeaderShimmer()
    }
}