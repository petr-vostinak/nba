package cz.vostinak.presentation.screens.player

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import cz.vostinak.domain.core.entities.DataOrigin
import cz.vostinak.domain.core.entities.Player
import cz.vostinak.domain.core.entities.Team
import cz.vostinak.domain.favorite.usecase.AddFavoritePlayerUseCase
import cz.vostinak.domain.favorite.usecase.IsFavoritePlayerUseCase
import cz.vostinak.domain.favorite.usecase.RemoveFavoritePlayerUseCase
import cz.vostinak.domain.core.usecases.GetPlayerUseCase
import cz.vostinak.presentation.mapper.toState
import cz.vostinak.presentation.screens.player.state.PlayerState
import cz.vostinak.presentation.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class PlayerDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var getPlayerUseCase: GetPlayerUseCase
    private lateinit var addFavoritePlayerUseCase: AddFavoritePlayerUseCase
    private lateinit var removeFavoritePlayerUseCase: RemoveFavoritePlayerUseCase
    private lateinit var isFavoritePlayerUseCase: IsFavoritePlayerUseCase
    private lateinit var viewModel: PlayerDetailViewModel

    private val testPlayer = Player(
        id = 1,
        firstName = "Test",
        lastName = "Player",
        position = "G",
        team = Team(
            id = 1,
            abbreviation = "TST",
            city = "Test City",
            conference = "Test Conference",
            division = "Test Division",
            fullName = "Test Team",
            name = "Test Team",
            logoResourceIdRes = null,
            origin = DataOrigin.API
        ),
        height = "6-0",
        weight = "180",
        college = "Test College",
        draftYear = "2023",
        draftRound = "1",
        draftNumber = "1",
        country = "Test Country",
        jerseyNumber = "1",
        origin = DataOrigin.API
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getPlayerUseCase = Mockito.mock(GetPlayerUseCase::class.java)
        viewModel = PlayerDetailViewModel(getPlayerUseCase, addFavoritePlayerUseCase, removeFavoritePlayerUseCase, isFavoritePlayerUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getPlayerDetail success`() = runTest {
        // Given
        whenever(getPlayerUseCase(1)).thenReturn(flow<Player> { testPlayer })

        // When
        viewModel.getPlayerDetail(1)
        testDispatcher.scheduler.advanceTimeBy(30 * 1000)

        // Then
        val state = viewModel.playerDetailState.value
        Assert.assertEquals(UiState<PlayerState>.Success(testPlayer.toState()), state)
    }

    @Test
    fun `getPlayerDetail error`() = runTest {
        // Given
        val exception = Exception("Test Exception")
        whenever(getPlayerUseCase(1)).thenThrow(exception)

        // When
        viewModel.getPlayerDetail(1)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = viewModel.playerDetailState.value
        Assert.assertEquals(UiState.Error(Exception()), state)
    }

    @Test
    fun `getPlayerDetail loading`() = runTest {
        // Given
        whenever(getPlayerUseCase(1)).thenReturn(flow { testPlayer })

        // When
        viewModel.getPlayerDetail(1)

        // Then
        val state = viewModel.playerDetailState.value
        Assert.assertEquals(UiState.Loading, state)
    }
}