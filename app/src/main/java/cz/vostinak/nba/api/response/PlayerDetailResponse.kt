package cz.vostinak.nba.api.response

import cz.vostinak.nba.ui.gui.list.model.Player

/**
 * Player detail response.
 */
data class PlayerDetailResponse(
    /** Player data */
    val data: Player
)
