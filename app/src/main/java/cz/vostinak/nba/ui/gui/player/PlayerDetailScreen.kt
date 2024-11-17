package cz.vostinak.nba.ui.gui.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import cz.vostinak.nba.R
import cz.vostinak.nba.ui.gui.list.model.Player
import cz.vostinak.nba.ui.gui.player.composables.InfoCell
import cz.vostinak.nba.ui.gui.player.composables.SectionHeader
import cz.vostinak.nba.ui.gui.player.model.PlayerDetailState
import cz.vostinak.nba.ui.gui.team.model.Team
import cz.vostinak.nba.ui.theme.NBATheme

/**
 * Player detail screen.
 * @param state Player detail state
 * @param onBack listener for back action
 * @param onTeamClick listener for team click
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun PlayerDetailScreen(
    state: PlayerDetailState,
    onBack: (() -> Unit)? = null,
    onTeamClick: ((Long) -> Unit)? = null
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(
                        stringResource(R.string.app_name),
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBack?.invoke()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GlideImage(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    model = "https://randomuser.me/api/portraits/men/${state.player?.jerseyNumber}.jpg",
                    contentDescription = "Player image",
                )

                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(
                        modifier = Modifier,
                        text = "${state.player?.lastName ?: ""} ${state.player?.firstName ?: ""}",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        modifier = Modifier,
                        text = "#${state.player?.jerseyNumber ?: "NA"} | ${state.player?.position ?: "---"} | ${state.player?.country ?: "---"}",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.LightGray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            SectionHeader(stringResource(R.string.section_player_basic))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                InfoCell(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.label_player_height),
                    value = state.player?.height ?: "---"
                )

                Spacer(
                    modifier = Modifier.size(8.dp)
                )

                InfoCell(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.label_player_weight),
                    value = (state.player?.weight ?: "---") + " lbs"
                )

            }

            SectionHeader(stringResource(R.string.section_player_team))

            InfoCell(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
                title = stringResource(R.string.label_player_team),
                value = state.player?.team?.fullName ?: "---",
                onClick = {
                    onTeamClick?.invoke(state.player?.team?.id ?: -1)
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            ) {
                InfoCell(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.label_player_conference),
                    value = state.player?.team?.conference ?: "---"
                )

                Spacer(
                    modifier = Modifier.size(8.dp)
                )

                InfoCell(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.label_player_division),
                    value = state.player?.team?.division ?: "---"
                )

                Spacer(
                    modifier = Modifier.size(8.dp)
                )

                InfoCell(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.label_player_city),
                    value = state.player?.team?.city ?: "---"
                )
            }

            SectionHeader(stringResource(R.string.section_player_draft))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                InfoCell(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.label_player_draft_year),
                    value = state.player?.draftYear?.toString() ?: "---"
                )

                Spacer(
                    modifier = Modifier.size(8.dp)
                )

                InfoCell(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.label_player_draft_round),
                    value = state.player?.draftRound?.toString() ?: "---"
                )

                Spacer(
                    modifier = Modifier.size(8.dp)
                )

                InfoCell(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.label_player_draft_number),
                    value = state.player?.draftNumber?.toString() ?: "---"
                )
            }
        }
    }
}

@Preview
@Composable
private fun ShowPlayerDetailScreen() {
    NBATheme {
        PlayerDetailScreen(
            state = PlayerDetailState(
                player = Player(
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
                ),
                isLoading = false,
                error = null
            )
        )
    }
}