package cz.vostinak.presentation.screens.team.composables

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
import cz.vostinak.presentation.components.infocell.InfoCell
import cz.vostinak.presentation.components.sectionheader.SectionHeader
import cz.vostinak.presentation.R
import cz.vostinak.presentation.screens.team.state.TeamState

/**
 * TeamState detail content.
 * @param modifier Modifier
 * @param state TeamState detail state
 */
@Composable
fun TeamDetailContent(
    modifier: Modifier,
    state: TeamState
) {
    Column(
        modifier = modifier
    ) {
        state.logoResourceIdRes?.let {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
                    .background(Color.White)
                    .padding(32.dp),
                painter = painterResource(it),
                contentDescription = stringResource(R.string.content_description_team_logo, state.fullName)
            )
        }

        SectionHeader(stringResource(R.string.section_team_basic))

        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            InfoCell(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.label_team_city),
                value = state.city
            )

            Spacer(modifier = Modifier.width(8.dp))

            InfoCell(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.label_team_name),
                value = state.name
            )

            Spacer(modifier = Modifier.width(8.dp))

            InfoCell(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.label_team_abbreviation),
                value = state.abbreviation
            )
        }

        SectionHeader(stringResource(R.string.section_team_whereabouts))

        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            InfoCell(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.label_team_division),
                value = state.division
            )

            Spacer(modifier = Modifier.width(8.dp))

            InfoCell(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.label_team_conference),
                value = state.conference
            )
        }
    }
}