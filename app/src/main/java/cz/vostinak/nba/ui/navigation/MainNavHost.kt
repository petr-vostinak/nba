package cz.vostinak.nba.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.vostinak.presentation.screens.team.TeamDetailScreen
import cz.vostinak.presentation.screens.list.PlayersListScreen
import cz.vostinak.presentation.screens.player.PlayerDetailScreen

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
            PlayersListScreen(
                onPlayerDetailClick = { playerId ->
                    navController.navigate(Screens.PlayerDetail.createRoute(playerId))
                }
            )
        }

        // PlayerItemState detail
        composable(route = Screens.PlayerDetail.route) { backStackEntry ->
            val playerId = backStackEntry.arguments?.getString("playerId")?.toLong()

            PlayerDetailScreen(
                playerId = playerId ?: 0,
                onBack = {
                    navController.popBackStack()
                },
                onTeamClick = { teamId ->
                    navController.navigate(Screens.TeamsDetail.createRoute(teamId))
                }
            )
        }

        // TeamState detail
        composable(route = Screens.TeamsDetail.route) { backStackEntry ->
            val teamId = backStackEntry.arguments?.getString("teamId")?.toLong()

            TeamDetailScreen(
                teamId = teamId ?: 0,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}