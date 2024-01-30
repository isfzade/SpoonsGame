package az.isfan.spoonsgame.Screens.Menu.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item{
            ItemInMenu(
                text = stringResource(R.string.three_players),
                onClick = onThreePlayersButtonClick
            )
        }

        item{
            ItemInMenu(
                text = stringResource(R.string.four_players),
                onClick = onFourPlayersButtonClick
            )
        }

        item{
            ItemInMenu(
                text = stringResource(R.string.five_players),
                onClick = onFivePlayersButtonClick
            )
        }

        item{
            ItemInMenu(
                text = stringResource(R.string.high_scores),
                onClick = onHighScoresButtonClick
            )
        }

        item{
            ItemInMenu(
                text = stringResource(R.string.load_last_game),
                onClick = onLoadLastGameButtonClick
            )
        }
    }
}