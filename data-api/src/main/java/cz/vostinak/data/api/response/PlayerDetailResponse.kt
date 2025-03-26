package cz.vostinak.data.api.response

import cz.vostinak.data.api.to.PlayerTO

/**
 * Player detail response.
 */
data class PlayerDetailResponse(
    /** Player data */
    val data: PlayerTO
)
