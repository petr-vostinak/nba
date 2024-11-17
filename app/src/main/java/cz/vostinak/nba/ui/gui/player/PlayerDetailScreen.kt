package cz.vostinak.nba.ui.gui.player

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import cz.vostinak.nba.R
import cz.vostinak.nba.ui.gui.commons.ErrorCard
import cz.vostinak.nba.ui.gui.list.model.Player
import cz.vostinak.nba.ui.gui.player.composables.PlayerDetailContent
import cz.vostinak.nba.ui.gui.player.composables.PlayerDetailShimmer
import cz.vostinak.nba.ui.gui.player.model.PlayerDetailState
import cz.vostinak.nba.ui.gui.team.model.Team
import cz.vostinak.nba.ui.preview.Theme
import cz.vostinak.nba.ui.preview.ThemePreviewProvider
import cz.vostinak.nba.ui.theme.NBATheme

/**
 * Player detail screen.
 * @param state Player detail state
 * @param onBack listener for back action
 * @param onTeamClick listener for team click
 */
@OptIn(ExperimentalMaterial3Api::class)
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
                            contentDescription = stringResource(R.string.content_description_back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        // Show loading shimmer
        AnimatedVisibility(
            visible = state.isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            PlayerDetailShimmer(
                modifier = Modifier.padding(innerPadding)
            )
        }

        // Show content
        AnimatedVisibility(
            visible = !state.isLoading && state.error == null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            PlayerDetailContent(
                modifier = Modifier.padding(innerPadding),
                state = state,
                onTeamClick = onTeamClick
            )
        }

        // Show error
        AnimatedVisibility(
            visible = state.error != null && !state.isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            ErrorCard(
                modifier = Modifier.padding(innerPadding)
            )
        }

    }
}

@Preview
@Composable
private fun ShowPlayerDetailScreen(@PreviewParameter(ThemePreviewProvider ::class) theme: Theme) {
    NBATheme(
        darkTheme = theme.isDarkMode
    ) {
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

@Preview
@Composable
private fun ShowPlayerDetailScreenLoading(@PreviewParameter(ThemePreviewProvider ::class) theme: Theme) {
    NBATheme(
        darkTheme = theme.isDarkMode
    ) {
        PlayerDetailScreen(
            state = PlayerDetailState(
                player = null,
                isLoading = true,
                error = null
            )
        )
    }
}

@Preview
@Composable
private fun ShowPlayerDetailScreenError(@PreviewParameter(ThemePreviewProvider ::class) theme: Theme) {
    NBATheme(
        darkTheme = theme.isDarkMode
    ) {
        PlayerDetailScreen(
            state = PlayerDetailState(
                player = null,
                isLoading = false,
                error = Throwable()
            )
        )
    }
}