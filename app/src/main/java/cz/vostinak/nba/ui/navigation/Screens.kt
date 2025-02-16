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
     * PlayerItemState detail.
     */
    object PlayerDetail: Screens("player_detail/{playerId}") {
        /**
         * Create route.
         * @param playerId PlayerItemState ID
         */
        fun createRoute(playerId: Long): String {
            return "player_detail/$playerId"
        }
    }

    /**
     * Teams detail.
     */
    object TeamsDetail: Screens("team_detail/{teamId}") {
        /**
         * Create route.
         * @param teamId TeamState ID
         */
        fun createRoute(teamId: Long): String {
            return "team_detail/$teamId"
        }
    }
}