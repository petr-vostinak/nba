package cz.vostinak.nba.ui.gui.list.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Players list view model.
 */
@HiltViewModel
class PlayersListViewModel @Inject constructor(
    private val repository: PlayersListRepository
): ViewModel() {

    /** Players list state. */
    val playersListState = repository.playersListState.asStateFlow()

    /**
     * Initial load of players.
     */
    fun initialLoad() {
        viewModelScope.launch(Dispatchers.Default) {
            repository.initLoadPlayers()
        }
    }

    /**
     * Load more players.
     */
    fun loadMore() {
        viewModelScope.launch(Dispatchers.Default) {
            repository.loadNexPage()
        }
    }

}