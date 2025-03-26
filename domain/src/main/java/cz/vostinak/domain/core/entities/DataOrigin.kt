package cz.vostinak.domain.core.entities

/**
 * Data origin.
 */
enum class DataOrigin {
    /** Data from database, but expired */
    DB_EXPIRED,
    /** Data from database, actual */
    DB_CURRENT,
    /** Data from API */
    API
}