package cz.vostinak.nba.ui.gui.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import cz.vostinak.nba.R
import cz.vostinak.nba.ui.gui.list.composables.PlayerItem
import cz.vostinak.nba.ui.gui.list.composables.PlayerItemShimmer
import cz.vostinak.nba.ui.gui.list.model.Player
import cz.vostinak.nba.ui.gui.list.model.PlayersListState
import cz.vostinak.nba.ui.gui.team.model.Team
import cz.vostinak.core.ui.preview.Theme
import cz.vostinak.core.ui.preview.ThemePreviewProvider
import cz.vostinak.core.ui.theme.NBATheme
import cz.vostinak.presentation.components.errorcard.ErrorCard


/**
 * Players list screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayersListScreen(
    state: PlayersListState,
    onLoadNextPage: (() -> Unit)? = null,
    onPlayerDetailClick: ((Long) -> Unit)? = null
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
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            AnimatedVisibility(
                visible = state.error != null && !state.isLoading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                ErrorCard(
                    modifier = Modifier
                )
            }

            val lazyListState = rememberLazyListState()

            // innit loading
            LaunchedEffect(lazyListState.canScrollForward) {
                if (lazyListState.canScrollForward.not() && lazyListState.firstVisibleItemIndex > 1) {
                    onLoadNextPage?.invoke()
                }
            }

            // list of players
            LazyColumn(
                modifier = Modifier.weight(1f),
                state = lazyListState
            ) {
                if(state.players.isEmpty() && state.isLoading) {
                    items(
                        count = 10
                    ) {
                        PlayerItemShimmer()
                    }
                }

                items(
                    count = state.players.size,
                    key = { index ->
                        state.players[index].id
                    }
                ) { index ->
                    val player = state.players[index]
                    PlayerItem(
                        modifier = Modifier.clickable {
                            onPlayerDetailClick?.invoke(player.id)
                        },
                        data = player
                    )
                }
            }

            // next page loading
            AnimatedVisibility(state.players.isNotEmpty() && state.isLoading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )
            }
        }

    }
}

@Preview
@Composable
private fun ShowPlayersListScreen(@PreviewParameter(ThemePreviewProvider ::class) theme: Theme) {
    NBATheme(
        darkTheme = theme.isDarkMode
    ) {
        PlayersListScreen(
            state = PlayersListState(
                players = listOf(
                    Player(
                        id = 1,
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
                            id = 1,
                            name = "Hawks",
                            fullName = "Atlanta Hawks",
                            city = "Atlanta",
                            abbreviation = "ATL",
                            conference = "East",
                            division = "Southeast"
                        )
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
private fun ShowPlayersListScreenLoading(@PreviewParameter(ThemePreviewProvider ::class) theme: Theme) {
    NBATheme(
        darkTheme = theme.isDarkMode
    ) {
        PlayersListScreen(
            state = PlayersListState(
                players = emptyList(),
                isLoading = true,
                error = null
            )
        )
    }
}

@Preview
@Composable
private fun ShowPlayersListScreenError(@PreviewParameter(ThemePreviewProvider ::class) theme: Theme) {
    NBATheme(
        darkTheme = theme.isDarkMode
    ) {
        PlayersListScreen(
            state = PlayersListState(
                players = emptyList(),
                isLoading = false,
                error = Throwable()
            )
        )
    }
}