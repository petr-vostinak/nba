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
                state = state.value
            )
        }
    }
}