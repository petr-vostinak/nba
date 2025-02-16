package cz.vostinak.presentation.screens.list

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cz.vostinak.core.ui.preview.Theme
import cz.vostinak.core.ui.preview.ThemePreviewProvider
import cz.vostinak.core.ui.theme.NBATheme
import cz.vostinak.presentation.components.errorcard.ErrorCard
import cz.vostinak.presentation.components.playeritem.PlayerItem
import cz.vostinak.presentation.components.playeritem.PlayerItemShimmer
import cz.vostinak.presentation.screens.list.state.PlayerItemState
import cz.vostinak.presentation.screens.list.state.PlayersListState
import cz.vostinak.presentation.R

/**
 * Players list screen.
 * @param viewModel PlayersListViewModel
 * @param onPlayerDetailClick listener for player detail click
 */
@Composable
fun PlayersListScreen(
    viewModel: PlayersListViewModel = hiltViewModel(),
    onPlayerDetailClick: ((Long) -> Unit)? = null
) {
    val state by viewModel.playersListState.collectAsStateWithLifecycle()

    PlayersListScreen(
        state = state,
        onLoadNextPage = {
            viewModel.loadMore()
        },
        onPlayerDetailClick = { playerId ->
            onPlayerDetailClick?.invoke(playerId)
        },
        onRetry = {
            viewModel.initialLoad()
        }
    )
}

/**
 * Players list screen.
 * @param state PlayersListState
 * @param onLoadNextPage listener for load next page
 * @param onPlayerDetailClick listener for player detail click
 * @param onRetry listener for retry
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PlayersListScreen(
    state: PlayersListState,
    onLoadNextPage: (() -> Unit)? = null,
    onPlayerDetailClick: ((Long) -> Unit)? = null,
    onRetry: (() -> Unit)? = null
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
                ) {
                    onRetry?.invoke()
                }
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
                        state = player
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
                    PlayerItemState(
                        id = 1,
                        fullName = "Stephen Curry",
                        jerseyNumber = "30",
                        teamFullName = "Atlanta Hawks",
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