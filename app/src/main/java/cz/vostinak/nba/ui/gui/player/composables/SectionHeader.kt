package cz.vostinak.nba.ui.gui.player.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.vostinak.nba.ui.theme.NBATheme

/**
 * Player info section title.
 * @param title section title
 */
@Composable
fun SectionHeader(title: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        text = title,
        style = MaterialTheme.typography.titleSmall,
        color = Color.White
    )
}

@Preview
@Composable
private fun ShowSectionHeader() {
    NBATheme {
        SectionHeader("Player Info")
    }
}