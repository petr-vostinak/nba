package cz.vostinak.presentation.screens.player

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cz.vostinak.core.ui.preview.Theme
import cz.vostinak.core.ui.preview.ThemePreviewProvider
import cz.vostinak.core.ui.theme.NBATheme
import cz.vostinak.presentation.components.errorcard.ErrorCard
import cz.vostinak.presentation.R
import cz.vostinak.presentation.mapper.StateDataOrigin
import cz.vostinak.presentation.screens.player.composables.PlayerDetailContent
import cz.vostinak.presentation.screens.player.composables.PlayerDetailShimmer
import cz.vostinak.presentation.screens.player.state.PlayerDetailScreenState
import cz.vostinak.presentation.screens.player.state.PlayerState
import cz.vostinak.presentation.screens.team.state.TeamState
import cz.vostinak.presentation.state.UiState

/**
 * PlayerItemState detail screen.
 * @param playerId PlayerItemState id
 * @param viewModel PlayerItemState detail view model
 * @param onBack listener for back action
 * @param onTeamClick listener for team click
 */
@Composable
fun PlayerDetailScreen(
    playerId: Long,
    viewModel: PlayerDetailViewModel = hiltViewModel(),
    onBack: (() -> Unit)? = null,
    onTeamClick: ((Long) -> Unit)? = null
) {
    val state by viewModel.playerDetailState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getPlayerDetail(playerId)
    }

    PlayerDetailScreen(
        state = state,
        onBack = onBack,
        onTeamClick = onTeamClick,
        onRetry = {
            viewModel.getPlayerDetail(playerId)
        },
        addToFavorites = { player ->
            viewModel.addFavoritePlayer(player)
        },
        removeFromFavorites = { playerId ->
            viewModel.removeFavoritePlayer(playerId)
        }
    )
}

/**
 * PlayerItemState detail screen.
 * @param state PlayerItemState detail state
 * @param onBack listener for back action
 * @param onTeamClick listener for team click
 * @param onRetry listener for retry
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PlayerDetailScreen(
    state: UiState<PlayerDetailScreenState>,
    onBack: (() -> Unit)? = null,
    onTeamClick: ((Long) -> Unit)? = null,
    onRetry: (() -> Unit)? = null,
    addToFavorites: ((PlayerState) -> Unit)? = null,
    removeFromFavorites: ((Long) -> Unit)? = null
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
                },
                actions = {
                    (state as? UiState.Success)?.data?.isFavorite?.let { isFavorite ->
                        Image(
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .clickable {
                                    if (isFavorite) {
                                        removeFromFavorites?.invoke(state.data.playerState.id)
                                    } else {
                                        addToFavorites?.invoke(state.data.playerState)
                                    }
                                },
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "favorite",
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                }
            )
        },
        bottomBar = {
            (state as? UiState.Success)?.data?.playerState?.origin?.let { origin ->
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = "Data origin: ${origin.name}",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            // Show loading shimmer
            AnimatedVisibility(
                visible = state is UiState.Loading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                PlayerDetailShimmer()
            }

            // Show content
            AnimatedVisibility(
                visible = state is UiState.Success,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                PlayerDetailContent(
                    state = (state as UiState.Success).data.playerState,
                    onTeamClick = onTeamClick
                )
            }

            // Show error
            AnimatedVisibility(
                visible = state is UiState.Error,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                ErrorCard {
                    onRetry?.invoke()
                }
            }
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
            state = UiState.Success(
                data = PlayerDetailScreenState(
                    playerState = PlayerState(
                        id = 19,
                        firstName = "Stephen",
                        lastName = "Curry",
                        position = "G",
                        height = "6-2",
                        weight = "185",
                        jerseyNumber = "30",
                        college = "Davidson",
                        country = "USA",
                        draftYear = "2009",
                        draftRound = "1",
                        draftNumber = "7",
                        team = TeamState(
                            id = 10,
                            abbreviation = "GSW",
                            city = "Golden State",
                            fullName = "Golden State Warriors",
                            name = "Warriors",
                            conference = "West",
                            division = "Pacific",
                            logoResourceIdRes = cz.vostinak.core.ui.R.drawable.gsw,
                            origin = StateDataOrigin.API
                        ),
                        origin = StateDataOrigin.API
                    ),
                    isFavorite = false
                )
            )
        )
    }
}

@Preview
@Composable
private fun ShowPlayerDetailScreenFavorite(@PreviewParameter(ThemePreviewProvider ::class) theme: Theme) {
    NBATheme(
        darkTheme = theme.isDarkMode
    ) {
        PlayerDetailScreen(
            state = UiState.Success(
                data = PlayerDetailScreenState(
                    playerState = PlayerState(
                        id = 19,
                        firstName = "Stephen",
                        lastName = "Curry",
                        position = "G",
                        height = "6-2",
                        weight = "185",
                        jerseyNumber = "30",
                        college = "Davidson",
                        country = "USA",
                        draftYear = "2009",
                        draftRound = "1",
                        draftNumber = "7",
                        team = TeamState(
                            id = 10,
                            abbreviation = "GSW",
                            city = "Golden State",
                            fullName = "Golden State Warriors",
                            name = "Warriors",
                            conference = "West",
                            division = "Pacific",
                            logoResourceIdRes = cz.vostinak.core.ui.R.drawable.gsw,
                            origin = StateDataOrigin.DB_CURRENT
                        ),
                        origin = StateDataOrigin.DB_CURRENT
                    ),
                    isFavorite = true
                )
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
            state = UiState.Loading
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
            state = UiState.Error(Throwable())
        )
    }
}