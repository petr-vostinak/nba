package cz.vostinak.presentation.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.vostinak.domain.usecases.GetPlayersListUseCase
import cz.vostinak.presentation.mapper.toState
import cz.vostinak.presentation.screens.list.state.PlayerItemState
import cz.vostinak.presentation.state.UiState
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
    private val getPlayersListUseCase: GetPlayersListUseCase
): ViewModel() {

    private val _playersListState = MutableStateFlow<UiState<List<PlayerItemState>>>(UiState.Loading)
    val playersListState = _playersListState.asStateFlow()

    private var cachedList: List<PlayerItemState> = emptyList()

    init {
        initialLoad()
    }

    /**
     * Initial load of players.
     */
    fun initialLoad() {
        viewModelScope.launch(Dispatchers.IO) {
            _playersListState.value = UiState.Loading

            try {
                val data = getPlayersListUseCase()
                cachedList = data.map { it.toState() }
                _playersListState.value = UiState.Success(cachedList, false)
            } catch (e: Exception) {
                _playersListState.value = UiState.Error(e)
            }
        }
    }

    /**
     * Load more players.
     */
    fun loadMore() {
        viewModelScope.launch(Dispatchers.IO) {
            _playersListState.value = UiState.Success(cachedList, true)

            try {
                val data = getPlayersListUseCase.loadNextPage()
                val nextPage = data.map { it.toState() }
                cachedList = cachedList + nextPage
                _playersListState.value = UiState.Success(cachedList, false)
            } catch (e: Exception) {
                _playersListState.value = UiState.Error(e)
            }
        }
    }

}