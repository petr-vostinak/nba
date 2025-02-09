package cz.vostinak.nba.ui.gui.team.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.vostinak.nba.R
import cz.vostinak.nba.ui.gui.commons.InfoCell
import cz.vostinak.nba.ui.gui.commons.SectionHeader
import cz.vostinak.nba.ui.gui.team.TeamLogoUtils
import cz.vostinak.nba.ui.gui.team.model.TeamDetailState

/**
 * Team detail content.
 * @param modifier Modifier
 * @param state Team detail state
 */
@Composable
fun TeamDetailContent(
    modifier: Modifier,
    state: TeamDetailState
) {
    Column(
        modifier = modifier
    ) {
        TeamLogoUtils.getLogoByAbbreviation(state.team?.abbreviation)?.let {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
                    .background(Color.White)
                    .padding(32.dp),
                painter = painterResource(it),
                contentDescription = stringResource(R.string.content_description_team_logo, state.team?.fullName ?: "")
            )
        }

        SectionHeader(stringResource(R.string.section_team_basic))

        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            InfoCell(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.label_team_city),
                value = state.team?.city ?: ""
            )

            Spacer(modifier = Modifier.width(8.dp))

            InfoCell(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.label_team_name),
                value = state.team?.name ?: ""
            )

            Spacer(modifier = Modifier.width(8.dp))

            InfoCell(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.label_team_abbreviation),
                value = state.team?.abbreviation ?: ""
            )
        }

        SectionHeader(stringResource(R.string.section_team_whereabouts))

        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            InfoCell(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.label_team_division),
                value = state.team?.division ?: ""
            )

            Spacer(modifier = Modifier.width(8.dp))

            InfoCell(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.label_team_conference),
                value = state.team?.conference ?: ""
            )
        }
    }
}