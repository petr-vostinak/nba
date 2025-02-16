package cz.vostinak.presentation.screens.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.vostinak.data.repository.PlayerDetailRepository
import cz.vostinak.presentation.screens.player.state.PlayerDetailState
import cz.vostinak.presentation.screens.player.state.PlayerState
import cz.vostinak.presentation.screens.team.state.TeamState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * PlayerItemState detail view model.
 */
@HiltViewModel
class PlayerDetailViewModel @Inject constructor(
    private val repository: PlayerDetailRepository
) : ViewModel() {

    private val _playerDetailState = MutableStateFlow(PlayerDetailState())
    val playerDetailState = _playerDetailState.asStateFlow()

    /**
     * Get player detail.
     * @param playerId PlayerItemState ID.
     */
    fun getPlayerDetail(playerId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _playerDetailState.value = PlayerDetailState()

            try {
                val response = repository.getPlayerDetail(playerId)

                val playerDetail = PlayerState(
                    id = response.id,
                    firstName = response.firstName ?: "",
                    lastName = response.lastName ?: "",
                    position = response.position ?: "",
                    team = TeamState(
                        id = response.team?.id ?: 0,
                        abbreviation = response.team?.abbreviation ?: "",
                        city = response.team?.city ?: "",
                        conference = response.team?.conference ?: "",
                        division = response.team?.division ?: "",
                        fullName = response.team?.fullName ?: "",
                        name = response.team?.name ?: ""
                    ),
                    height = response.height ?: "",
                    weight = response.weight ?: "",
                    college = response.college ?: "",
                    draftYear = response.draftYear.toString(),
                    draftRound = response.draftRound.toString(),
                    draftNumber = response.draftNumber.toString(),
                    country = response.country ?: "",
                    jerseyNumber = response.jerseyNumber.toString()
                )

                _playerDetailState.value = _playerDetailState.value.copy(
                    isLoading = false,
                    player = playerDetail
                )
            } catch (e: Exception) {
                _playerDetailState.value = _playerDetailState.value.copy(
                    isLoading = false,
                    error = e
                )
            }
        }
    }
}