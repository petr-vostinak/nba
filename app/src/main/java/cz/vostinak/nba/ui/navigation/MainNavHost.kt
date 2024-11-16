package cz.vostinak.nba.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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

        }
    }
}