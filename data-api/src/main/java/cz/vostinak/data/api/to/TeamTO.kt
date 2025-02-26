package cz.vostinak.data.api.to

import com.google.gson.annotations.SerializedName

/**
 * Team data class.
 */
data class TeamTO(
    /** Team ID */
    val id: Long,
    /** Team short name */
    val name: String?,
    /** Team full name */
    @SerializedName("full_name")
    val fullName: String?,
    /** Team city */
    val city: String?,
    /** Team abbreviation */
    val abbreviation: String?,
    /** Team conference */
    val conference: String?,
    /** Team division */
    val division: String?
)
