package cz.vostinak.nba.ui.gui.team

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import cz.vostinak.nba.R
import cz.vostinak.nba.ui.gui.player.composables.InfoCell
import cz.vostinak.nba.ui.gui.player.composables.SectionHeader
import cz.vostinak.nba.ui.gui.team.model.Team
import cz.vostinak.nba.ui.gui.team.model.TeamDetailState
import cz.vostinak.nba.ui.preview.Theme
import cz.vostinak.nba.ui.preview.ThemePreviewProvider
import cz.vostinak.nba.ui.theme.NBATheme

/**
 * Team detail screen.
 * @param state team detail data
 * @param onBack listener for back action
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamDetailScreen(
    state: TeamDetailState,
    onBack: (() -> Unit)? = null
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
                        text = state.team?.fullName ?: "",
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
                            contentDescription = stringResource(R.string.content_description_back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            TeamLogoUtils.getLogoByAbbreviation(state.team?.abbreviation)?.let {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.5f),
                    painter = painterResource(it),
                    contentDescription = stringResource(R.string.content_description_team_logo, state.team?.fullName ?: "")
                )
            }

            SectionHeader(stringResource(R.string.section_team_basic))

            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                InfoCell(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.label_team_city),
                    value = state.team?.city ?: ""
                )

                Spacer(modifier = Modifier.width(8.dp))

                InfoCell(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.label_team_name),
                    value = state.team?.name ?: ""
                )

                Spacer(modifier = Modifier.width(8.dp))

                InfoCell(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.label_team_abbreviation),
                    value = state.team?.abbreviation ?: ""
                )
            }

            SectionHeader(stringResource(R.string.section_team_whereabouts))

            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                InfoCell(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.label_team_division),
                    value = state.team?.division ?: ""
                )

                Spacer(modifier = Modifier.width(8.dp))

                InfoCell(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.label_team_conference),
                    value = state.team?.conference ?: ""
                )
            }
        }
    }
}

@Preview
@Composable
private fun ShowTeamDetailScreen(@PreviewParameter(ThemePreviewProvider ::class) theme: Theme) {
    NBATheme(
        darkTheme = theme.isDarkMode
    ) {
        TeamDetailScreen(
            TeamDetailState(
                team = Team(
                    id = 1,
                    name = "Hawks",
                    fullName = "Atlanta Hawks",
                    city = "Atlanta",
                    abbreviation = "ATL",
                    conference = "East",
                    division = "Southeast"
                )
            )
        )
    }
}