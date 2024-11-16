package cz.vostinak.nba.ui.gui.list.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.vostinak.nba.ui.gui.list.model.Player
import cz.vostinak.nba.ui.theme.NBATheme

/**
 * Player item card.
 * @param modifier Modifier
 * @param data Player data
 */
@Composable
fun PlayerItem(
    modifier: Modifier,
    data: Player
) {
    Card(
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                modifier = Modifier,
                text = "${data.lastName} ${data.firstName}",
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                modifier = Modifier,
                text = data.country,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Preview
@Composable
private fun ShowPlayerItem() {
    NBATheme {
        PlayerItem(
            modifier = Modifier,
            data = Player(
                id = 19,
                firstName = "Stephen",
                lastName = "Curry",
                position = "G",
                height = "6-2",
                weight = "185",
                jerseyNumber = "30",
                college = "Davidson",
                country = "USA",
                draftYear = 2009,
                draftRound = 1,
                draftNumber = 7,
            )
        )
    }
}