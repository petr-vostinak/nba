package cz.vostinak.presentation.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.vostinak.data.repository.PlayersListRepository
import cz.vostinak.presentation.screens.list.state.PlayerItemState
import cz.vostinak.presentation.screens.list.state.PlayersListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
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

    private val _playersListState = MutableStateFlow(PlayersListState())
    val playersListState = _playersListState.asStateFlow()

    init {
        initialLoad()
    }

    /**
     * Initial load of players.
     */
    fun initialLoad() {
        viewModelScope.launch(Dispatchers.IO) {
            _playersListState.value = PlayersListState()

            try {
                val response = repository.initLoadPlayers()

                val list = response.map {
                    PlayerItemState(
                        id = it.id,
                        fullName = "${it.firstName} ${it.lastName}",
                        teamFullName = it.team?.fullName ?: "",
                        jerseyNumber = it.jerseyNumber.toString()
                    )
                }

                _playersListState.value = PlayersListState(
                    isLoading = false,
                    players = list
                )
            } catch (e: Exception) {
                _playersListState.value = PlayersListState(
                    isLoading = false,
                    players = emptyList(),
                    error = e
                )
            }
        }
    }

    /**
     * Load more players.
     */
    fun loadMore() {
        viewModelScope.launch(Dispatchers.IO) {
            _playersListState.value = _playersListState.value.copy(isLoading = true)

            try {
                val response = repository.loadNexPage()

                val nextPage = response.map {
                    PlayerItemState(
                        id = it.id,
                        fullName = "${it.firstName} ${it.lastName}",
                        teamFullName = it.team?.fullName ?: "",
                        jerseyNumber = it.jerseyNumber.toString()
                    )
                }

                _playersListState.value = _playersListState.value.copy(
                    isLoading = false,
                    players = _playersListState.value.players + nextPage
                )
            } catch (e: Exception) {
                _playersListState.value = PlayersListState(
                    isLoading = false,
                    players = emptyList(),
                    error = e
                )
            }
        }
    }

}