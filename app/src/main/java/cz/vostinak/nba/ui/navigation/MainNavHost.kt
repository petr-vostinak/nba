package cz.vostinak.nba.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.vostinak.nba.ui.gui.list.PlayersListScreen
import cz.vostinak.nba.ui.gui.list.model.PlayersListViewModel
import cz.vostinak.nba.ui.gui.player.PlayerDetailScreen
import cz.vostinak.nba.ui.gui.player.model.PlayerDetailViewModel

/**
 * Main navigation.
 */
@Composable
fun MainNavHost() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.PlayersList.route
    ) {
        // List of players
        composable(route = Screens.PlayersList.route) {
            val viewModel = hiltViewModel<PlayersListViewModel>()
            val state = viewModel.playersListState.collectAsState()

            LaunchedEffect(Unit) {
                viewModel.initialLoad()
            }

            PlayersListScreen(
                state = state.value,
                onLoadNextPage = {
                    viewModel.loadMore()
                },
                onPlayerDetailClick = { playerId ->
                    navController.navigate(Screens.PlayerDetail.createRoute(playerId))
                }
            )
        }

        // Player detail
        composable(route = Screens.PlayerDetail.route) { backStackEntry ->
            val viewModel = hiltViewModel<PlayerDetailViewModel>()
            val state = viewModel.playerDetailState.collectAsState()

            val playerId = backStackEntry.arguments?.getString("playerId")?.toLong()

            LaunchedEffect(Unit) {
                playerId?.let {
                    viewModel.getPlayerDetail(it)
                }
            }

            PlayerDetailScreen(
                state = state.value,
                onBack = {
                    navController.popBackStack()
                },
                onTeamClick = {
                    // TODO: navigate to team detail
                }
            )
        }
    }
}