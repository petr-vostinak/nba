package cz.vostinak.nba.ui.gui.team

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
import cz.vostinak.nba.ui.gui.team.composables.TeamDetailContent
import cz.vostinak.nba.ui.gui.team.composables.TeamDetailShimmer
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
        // Show loading shimmer
        AnimatedVisibility(
            visible = state.isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            TeamDetailShimmer(
                modifier = Modifier.padding(innerPadding)
            )
        }

        // Show content
        AnimatedVisibility(
            visible = !state.isLoading && state.error == null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            TeamDetailContent(
                modifier = Modifier.padding(innerPadding),
                state = state
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
private fun ShowTeamDetailScreen(@PreviewParameter(ThemePreviewProvider ::class) theme: Theme) {
    NBATheme(
        darkTheme = theme.isDarkMode
    ) {
        TeamDetailScreen(
            TeamDetailState(
                isLoading = false,
                team = Team(
                    id = 1,
                    name = "Hawks",
                    fullName = "Atlanta Hawks",
                    city = "Atlanta",
                    abbreviation = "ATL",
                    conference = "East",
                    division = "Southeast"
                ),
                error = null
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
            TeamDetailState(
                isLoading = true,
                team = null,
                error = null
            )
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
            TeamDetailState(
                isLoading = false,
                team = null,
                error = Throwable()
            )
        )
    }
}