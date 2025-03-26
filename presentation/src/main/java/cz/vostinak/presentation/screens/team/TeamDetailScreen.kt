package cz.vostinak.presentation.screens.team

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import cz.vostinak.presentation.screens.team.composables.TeamDetailContent
import cz.vostinak.presentation.screens.team.composables.TeamDetailShimmer
import cz.vostinak.presentation.screens.team.state.TeamState
import cz.vostinak.presentation.state.UiState

/**
 * TeamState detail screen.
 * @param teamId team ID
 * @param viewModel TeamState detail view model
 * @param onBack listener for back action
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamDetailScreen(
    teamId: Long,
    viewModel: TeamDetailViewModel = hiltViewModel(),
    onBack: (() -> Unit)? = null
) {
   val state by viewModel.teamDetailState.collectAsStateWithLifecycle()

    LaunchedEffect(teamId) {
        viewModel.getTeamDetail(teamId)
    }

   TeamDetailScreen(
       state = state,
       onBack = onBack,
       onRetry = {
           viewModel.getTeamDetail(teamId)
       }
   )
}

/**
 * TeamState detail screen.
 * @param state team detail data
 * @param onBack listener for back action
 * @param onRetry listener for retry action
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TeamDetailScreen(
    state: UiState<TeamState>,
    onBack: (() -> Unit)? = null,
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
                        text = (state as? UiState.Success)?.data?.fullName ?: "",
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
        },
        bottomBar = {
            (state as? UiState.Success)?.data?.origin?.let { origin ->
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
            when (state) {
                is UiState.Loading -> {
                    TeamDetailShimmer()
                }

                is UiState.Success -> {
                    TeamDetailContent(state = state.data)
                }

                is UiState.Error -> {
                    ErrorCard {
                        onRetry?.invoke()
                    }
                }
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
            UiState.Success(
                data = TeamState(
                    id = 1,
                    name = "Hawks",
                    fullName = "Atlanta Hawks",
                    city = "Atlanta",
                    abbreviation = "ATL",
                    conference = "East",
                    division = "Southeast",
                    logoResourceIdRes = cz.vostinak.core.ui.R.drawable.atl,
                    origin = StateDataOrigin.API
                )
            )
        )
    }
}

@Preview
@Composable
private fun ShowTeamDetailShimmer(@PreviewParameter(ThemePreviewProvider ::class) theme: Theme) {
    NBATheme(
        darkTheme = theme.isDarkMode
    ) {
        TeamDetailScreen(
            UiState.Loading
        )
    }
}

@Preview
@Composable
private fun ShowTeamDetailError(@PreviewParameter(ThemePreviewProvider ::class) theme: Theme) {
    NBATheme(
        darkTheme = theme.isDarkMode
    ) {
        TeamDetailScreen(
            UiState.Error(Throwable())
        )
    }
}