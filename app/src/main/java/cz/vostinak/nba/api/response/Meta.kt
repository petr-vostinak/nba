package cz.vostinak.nba.api.response

import com.google.gson.annotations.SerializedName

/**
 * Paging metadata.
 */
data class Meta(
    /** Cursor for next page */
    @SerializedName("next_cursor")
    val nextCursor: Int,
    /** Items per page */
    @SerializedName("per_page")
    val perPage: Int,
)
