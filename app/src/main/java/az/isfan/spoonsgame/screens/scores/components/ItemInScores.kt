package az.isfan.spoonsgame.screens.scores.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.isfan.spoonsgame.R
import az.isfan.spoonsgame.data.enums.GameStatusEnum
import az.isfan.spoonsgame.data.models.GameData

@Composable
fun ItemInScores(
    game: GameData
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    space = 5.dp,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = "time"
                )

                Text(
                    text = game.dateTime!!,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }

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
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )

        Text(
            text = correspondingValue,
            color = when (correspondingValue) {
                GameStatusEnum.WON.name -> Color.Green
                GameStatusEnum.LOST.name -> Color.Red
                else -> Color.Black
            }
        )
    }
}