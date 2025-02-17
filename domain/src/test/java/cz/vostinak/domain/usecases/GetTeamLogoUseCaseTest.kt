package cz.vostinak.domain.usecases

import cz.vostinak.core.ui.R
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetTeamLogoUseCaseTest {

    private lateinit var getTeamLogoUseCase: GetTeamLogoUseCase

    @Before
    fun setup() {
        getTeamLogoUseCase = GetTeamLogoUseCase()
    }

    @Test
    fun `getTeamLogo success`() {
        // Given
        val abbreviation = "ATL"

        // When
        val logoId = getTeamLogoUseCase(abbreviation)

        // Then
        Assert.assertEquals(R.drawable.atl, logoId)
    }

    @Test
    fun `getTeamLogo null`() {
        // Given
        val abbreviation = "XXX"

        // When
        val logoId = getTeamLogoUseCase(abbreviation)

        // Then
        Assert.assertEquals(null, logoId)
    }
}