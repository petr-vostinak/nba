package cz.vostinak.nba.ui.navigation

/**
 * Screens.
 */
sealed class Screens(val route: String) {

    /**
     * Players list.
     */
    object PlayersList: Screens("players_list")

    /**
     * Player detail.
     */
    object PlayerDetail: Screens("player_detail/{playerId}") {
        /**
         * Create route.
         * @param playerId Player ID
         */
        fun createRoute(playerId: Long): String {
            return "player_detail/$playerId"
        }
    }
}