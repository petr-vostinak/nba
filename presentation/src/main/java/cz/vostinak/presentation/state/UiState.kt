package cz.vostinak.presentation.state

/**
 * State for UI components.
 */
sealed class UiState<out T> {
    /**
     * Loading state.
     */
    object Loading: UiState<Nothing>()
    /**
     * Success state with data and optional loading more flag.
     */
    data class Success<out T>(
        val data: T,
        val isLoadingMore: Boolean = false
    ): UiState<T>()
    /**
     * Error state.
     */
    data class Error(
        val error: Throwable
    ): UiState<Nothing>()
}