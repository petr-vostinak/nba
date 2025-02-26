package cz.vostinak.data.api.response

import cz.vostinak.data.api.to.MetaTO
import cz.vostinak.data.api.to.PlayerTO

/**
 * Players list response.
 */
data class PlayersListResponse(
    /** List of players. */
    val data: List<PlayerTO>,
    /** Paging metadata. */
    val meta: MetaTO
)
