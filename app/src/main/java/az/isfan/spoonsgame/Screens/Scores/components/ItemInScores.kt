package az.isfan.spoonsgame.Screens.Scores.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.stringResource
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
            ValueWithHeading(
                heading = stringResource(R.string.players),
                correspondingValue = game.playerCount.toString()
            )

            ValueWithHeading(
                heading = stringResource(R.string.rounds),
                correspondingValue = game.roundCount.toString()
            )

            ValueWithHeading(
                heading = stringResource(R.string.result),
                correspondingValue = game.status.toString()
            )
        }
    }
}

@Composable
fun ValueWithHeading(
    heading: String,
    correspondingValue: String,
) {
    Column {
        Text(
            text = heading
        )

        Text(
            text = correspondingValue
        )
    }
}