package cz.vostinak.domain.usecases

import cz.vostinak.data.api.to.PlayerTO
import cz.vostinak.data.api.to.TeamTO
import cz.vostinak.data.repository.PlayerDetailRepository
import cz.vostinak.domain.mapper.toDomain
import cz.vostinak.room.repository.PlayerDbRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetPlayerUseCaseTest {

    private lateinit var getPlayerUseCase: GetPlayerUseCase
    private lateinit var playerRepository: PlayerDetailRepository
    private lateinit var playerDbRepository: PlayerDbRepository

    private val testPlayer = PlayerTO(
        id = 1,
        firstName = "Test",
        lastName = "Player",
        position = "G",
        team = TeamTO(
            id = 1,
            abbreviation = "TST",
            city = "Test City",
            conference = "Test Conference",
            division = "Test Division",
            fullName = "Test Team",
            name = "Test Team"
        ),
        height = "6-0",
        weight = "180",
        college = "Test College",
        draftYear = 2023,
        draftRound = 1,
        draftNumber = 1,
        country = "Test Country",
        jerseyNumber = "1"
    )

    @Before
    fun setup() {
        playerRepository = Mockito.mock(PlayerDetailRepository::class.java)
        playerDbRepository = Mockito.mock(PlayerDbRepository::class.java)
        getPlayerUseCase = GetPlayerUseCase(playerRepository, playerDbRepository)
    }

    @Test
    fun `getPlayer success`() = runTest {
        // Given
        whenever(playerRepository.getPlayerDetail(1)).thenReturn(testPlayer)

        // When
        val player = getPlayerUseCase(1)

        // Then
        assertEquals(testPlayer.toDomain(), player)
    }

    @Test(expected = Exception::class)
    fun `getPlayer error`() = runTest {
        // Given
        val exception = Exception("Test Exception")
        whenever(playerRepository.getPlayerDetail(1)).thenThrow(exception)

        // When
        getPlayerUseCase(1)
    }
}