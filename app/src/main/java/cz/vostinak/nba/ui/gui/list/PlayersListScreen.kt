package cz.vostinak.nba.ui.gui.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.vostinak.nba.R
import cz.vostinak.nba.ui.gui.list.composables.PlayerItem
import cz.vostinak.nba.ui.gui.list.model.PlayersListState


/**
 * Players list screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayersListScreen(
    state: PlayersListState,
    onLoadNextPage: (() -> Unit)? = null,
    onPlayerDetailClick: ((Long) -> Unit)? = null
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
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            val lazyListState = rememberLazyListState()

            // innit loading
            LaunchedEffect(lazyListState.canScrollForward) {
                if (lazyListState.canScrollForward.not() && lazyListState.firstVisibleItemIndex > 1) {
                    onLoadNextPage?.invoke()
                }
            }

            // list of players
            LazyColumn(
                modifier = Modifier.weight(1f),
                state = lazyListState
            ) {
                if(state.players.isEmpty() && state.isLoading) {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        )
                    }
                }

                items(
                    count = state.players.size,
                    key = { index ->
                        state.players[index].id
                    }
                ) { index ->
                    val player = state.players[index]
                    PlayerItem(
                        modifier = Modifier.clickable {
                            onPlayerDetailClick?.invoke(player.id)
                        },
                        data = player
                    )
                }
            }

            // next page loading
            AnimatedVisibility(state.players.isNotEmpty() && state.isLoading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )
            }
        }

    }
}