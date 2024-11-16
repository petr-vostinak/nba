package cz.vostinak.nba.ui.gui.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import cz.vostinak.nba.R
import cz.vostinak.nba.ui.gui.list.composables.PlayerItem
import cz.vostinak.nba.ui.gui.list.model.PlayersListState


/**
 * Players list screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayersListScreen(
    state: PlayersListState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(
                        stringResource(R.string.app_name),
                        color = Color.White
                    )
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(
                count = state.players.size,
                key = { index ->
                    state.players[index].id
                }
            ) { index ->
                val player = state.players[index]
                PlayerItem(
                    modifier = Modifier,
                    data = player
                )
            }
        }
    }
}