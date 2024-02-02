package az.isfan.spoonsgame.Screens.Scores.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import az.isfan.spoonsgame.Data.Models.GameData
import az.isfan.spoonsgame.R

@Composable
fun ItemInScores(
    game: GameData
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = game.saveTimestamp.toString()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                ValueWithHeading(
                    heading = stringResource(R.string.players),
                    correspondingValue = game.playerCount.toString()
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                ValueWithHeading(
                    heading = stringResource(R.string.rounds),
                    correspondingValue = game.roundCount.toString()
                )

            }

            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                ValueWithHeading(
                    heading = stringResource(R.string.result),
                    correspondingValue = game.status.toString()
                )
            }
        }
    }
}

@Composable
fun ValueWithHeading(
    heading: String,
    correspondingValue: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = heading,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = correspondingValue
        )
    }
}