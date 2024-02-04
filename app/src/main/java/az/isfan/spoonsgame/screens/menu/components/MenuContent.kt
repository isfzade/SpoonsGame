package az.isfan.spoonsgame.screens.menu.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.isfan.spoonsgame.R

@Composable
fun MenuContent(
    onThreePlayersButtonClick: () -> Unit,
    onFourPlayersButtonClick: () -> Unit,
    onFivePlayersButtonClick: () -> Unit,
    onHighScoresButtonClick: () -> Unit,
    onLoadLastGameButtonClick: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        item{
            ItemInMenu(
                text = stringResource(R.string.three_players),
                icon = Icons.Default.PlayArrow,
                onClick = onThreePlayersButtonClick
            )
        }

        item{
            ItemInMenu(
                text = stringResource(R.string.four_players),
                icon = Icons.Default.PlayArrow,
                onClick = onFourPlayersButtonClick
            )
        }

        item{
            ItemInMenu(
                text = stringResource(R.string.five_players),
                icon = Icons.Default.PlayArrow,
                onClick = onFivePlayersButtonClick
            )
        }

        item{
            ItemInMenu(
                text = stringResource(R.string.high_scores),
                icon = Icons.Default.QueryStats,
                onClick = onHighScoresButtonClick
            )
        }

        item{
            ItemInMenu(
                text = stringResource(R.string.load_last_game),
                icon = Icons.Default.DateRange,
                onClick = onLoadLastGameButtonClick
            )
        }

        item {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                thickness = 2.dp,
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = stringResource(R.string.dev_info),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}