package az.isfan.spoonsgame.Screens.Game.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.R

@Composable
fun GameTable(
    availableCards: List<CardData>,
    discardedCards: List<CardData>
) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.available)
                )

                Box(
                    modifier = Modifier
                        .size(width = 35.dp, height = 60.dp)
                        .border(width = 2.dp, color = Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    if (availableCards.isNotEmpty()) {
                        Image(
                            painter = painterResource(R.drawable.back_dark),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.discarded)
                )

                Box(
                    modifier = Modifier
                        .size(width = 35.dp, height = 60.dp)
                        .border(width = 2.dp, color = Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    if (discardedCards.isNotEmpty()) {
                        Image(
                            painter = painterResource(R.drawable.back_dark),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}