package cz.vostinak.nba.ui.gui.list.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import cz.vostinak.nba.R
import cz.vostinak.nba.ui.gui.list.model.Player
import cz.vostinak.nba.ui.gui.team.model.Team
import cz.vostinak.core.ui.preview.Theme
import cz.vostinak.core.ui.preview.ThemePreviewProvider
import cz.vostinak.core.ui.theme.NBATheme

/**
 * Player item card.
 * @param modifier Modifier
 * @param data Player data
 */
@OptIn(ExperimentalGlideComposeApi::class)
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
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                model = "https://randomuser.me/api/portraits/men/${data.jerseyNumber}.jpg",
                contentDescription = stringResource(R.string.content_description_player_image),
                failure = placeholder(R.drawable.ic_user_image_placeholder)
            )

            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = "${data.lastName ?: ""} ${data.firstName ?: ""}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    modifier = Modifier,
                    text = data.team?.fullName ?: "",
                    style = MaterialTheme.typography.titleMedium,
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
                team = Team(
                    id = 10,
                    abbreviation = "GSW",
                    city = "Golden State",
                    fullName = "Golden State Warriors",
                    name = "Warriors",
                    conference = "West",
                    division = "Pacific"
                )
            )
        )
    }
}