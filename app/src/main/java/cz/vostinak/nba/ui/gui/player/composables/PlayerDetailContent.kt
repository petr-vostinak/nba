package cz.vostinak.nba.ui.gui.player.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import cz.vostinak.nba.R
import cz.vostinak.nba.ui.gui.commons.InfoCell
import cz.vostinak.nba.ui.gui.commons.SectionHeader
import cz.vostinak.nba.ui.gui.player.model.PlayerDetailState

/**
 * Player detail content.
 * @param modifier Modifier
 * @param state Player detail state
 * @param onTeamClick listener for team click
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlayerDetailContent(
    modifier: Modifier,
    state: PlayerDetailState,
    onTeamClick: ((Long) -> Unit)? = null
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                model = "https://randomuser.me/api/portraits/men/${state.player?.jerseyNumber}.jpg",
                contentDescription = stringResource(R.string.content_description_player_image),
                failure = placeholder(R.drawable.ic_user_image_placeholder)
            )

            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = "${state.player?.lastName ?: ""} ${state.player?.firstName ?: ""}",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    modifier = Modifier,
                    text = "#${state.player?.jerseyNumber ?: "NA"} | ${state.player?.position ?: "---"} | ${state.player?.country ?: "---"}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.LightGray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        SectionHeader(stringResource(R.string.section_player_basic))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            InfoCell(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.label_player_height),
                value = state.player?.height ?: "---"
            )

            Spacer(
                modifier = Modifier.size(8.dp)
            )

            InfoCell(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.label_player_weight),
                value = (state.player?.weight ?: "---") + " lbs"
            )

        }

        SectionHeader(stringResource(R.string.section_player_team))

        InfoCell(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
            title = stringResource(R.string.label_player_team),
            value = state.player?.team?.fullName ?: "---",
            onClick = {
                onTeamClick?.invoke(state.player?.team?.id ?: -1)
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        ) {
            InfoCell(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.label_player_conference),
                value = state.player?.team?.conference ?: "---"
            )

            Spacer(
                modifier = Modifier.size(8.dp)
            )

            InfoCell(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.label_player_division),
                value = state.player?.team?.division ?: "---"
            )

            Spacer(
                modifier = Modifier.size(8.dp)
            )

            InfoCell(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.label_player_city),
                value = state.player?.team?.city ?: "---"
            )
        }

        SectionHeader(stringResource(R.string.section_player_draft))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            InfoCell(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.label_player_draft_year),
                value = state.player?.draftYear?.toString() ?: "---"
            )

            Spacer(
                modifier = Modifier.size(8.dp)
            )

            InfoCell(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.label_player_draft_round),
                value = state.player?.draftRound?.toString() ?: "---"
            )

            Spacer(
                modifier = Modifier.size(8.dp)
            )

            InfoCell(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.label_player_draft_number),
                value = state.player?.draftNumber?.toString() ?: "---"
            )
        }
    }
}