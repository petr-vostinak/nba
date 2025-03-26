package cz.vostinak.presentation.mapper

/**
 * Data origin.
 */
enum class StateDataOrigin {
    /** Data from database, but expired */
    DB_EXPIRED,
    /** Data from database, actual */
    DB_CURRENT,
    /** Data from API */
    API
}