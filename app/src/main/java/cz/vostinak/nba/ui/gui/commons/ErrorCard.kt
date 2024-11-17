package cz.vostinak.nba.ui.gui.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import cz.vostinak.nba.R
import cz.vostinak.nba.ui.preview.Theme
import cz.vostinak.nba.ui.preview.ThemePreviewProvider
import cz.vostinak.nba.ui.theme.NBATheme

/**
 * Error card.
 * @param modifier Modifier
 */
@Composable
fun ErrorCard(
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image
            Image(
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = stringResource(id = R.string.error_card_image_desc),
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 20.dp)
                    .size(200.dp, 200.dp)
            )

            // Title
            Text(
                modifier = Modifier
                    .padding(start = 8.dp, top = 0.dp, end = 8.dp, bottom = 5.dp),
                text = stringResource(id = R.string.error_card_title),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            // Subtitle
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                text = stringResource(id = R.string.error_card_subtitle),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
private fun ShowErrorCard(@PreviewParameter(ThemePreviewProvider ::class) theme: Theme) {
    NBATheme(
        darkTheme = theme.isDarkMode
    ) {
        ErrorCard(
            modifier = Modifier
        )
    }
}