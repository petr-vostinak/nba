package cz.vostinak.data.api.to

import com.google.gson.annotations.SerializedName

/**
 * Player data class.
 */
data class PlayerTO(
    /** Player ID */
    val id: Long,
    /** First name */
    @SerializedName("first_name")
    val firstName: String?,
    /** Last name */
    @SerializedName("last_name")
    val lastName: String?,
    /** Position */
    val position: String?,
    /** Height */
    val height: String?,
    /** Weight */
    val weight: String?,
    /** Jersey number */
    @SerializedName("jersey_number")
    val jerseyNumber: String?,
    /** College */
    val college: String?,
    /** Country */
    val country: String?,
    /** Draft year */
    @SerializedName("draft_year")
    val draftYear: Int?,
    /** Draft round */
    @SerializedName("draft_round")
    val draftRound: Int?,
    /** Draft number */
    @SerializedName("draft_number")
    val draftNumber: Int?,
    /** Team info */
    val team: TeamTO?
)
