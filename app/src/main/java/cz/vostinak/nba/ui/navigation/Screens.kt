package cz.vostinak.nba.ui.navigation

/**
 * Screens.
 */
sealed class Screens(val route: String) {

    /**
     * Players list.
     */
    object PlayersList: Screens("players_list")
}