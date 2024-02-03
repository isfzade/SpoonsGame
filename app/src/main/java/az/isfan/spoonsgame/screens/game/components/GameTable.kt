package az.isfan.spoonsgame.screens.game.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FrontHand
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.isfan.spoonsgame.R
import az.isfan.spoonsgame.data.enums.GameStatusEnum
import az.isfan.spoonsgame.data.models.CardData

@Composable
fun GameTable(
    showTakeSpoonButton: Boolean,
    gameStatus: GameStatusEnum,
    onSpoonButtonClick: () -> Unit,
    availableCards: List<CardData>,
    discardedCards: List<CardData>
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .weight(4f),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(width = 200.dp, height = 200.dp)
                    .background(color = Color.Green.copy(alpha=0.4f))
                    .border(2.dp, Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        DeckCardInGameTable(
                            title = stringResource(R.string.available),
                            size = availableCards.size
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        DeckCardInGameTable(
                            title = stringResource(R.string.discarded),
                            size = discardedCards.size
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
            if (!showTakeSpoonButton) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(when(gameStatus) {
                        GameStatusEnum.LOST -> R.string.you_lost
                        GameStatusEnum.WON -> R.string.you_won
                        GameStatusEnum.NOT_FINISHED -> R.string.app_name
                    }),
                    fontWeight = if (gameStatus == GameStatusEnum.NOT_FINISHED) FontWeight.Normal else FontWeight.Bold,
                    fontSize = 25.sp,
                    color = when(gameStatus) {
                        GameStatusEnum.LOST -> Color.Red
                        GameStatusEnum.WON -> Color.Green
                        GameStatusEnum.NOT_FINISHED -> Color.Black
                    },
                    textAlign = TextAlign.Center
                )
            }
            else {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    onClick = onSpoonButtonClick,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.FrontHand,
                            contentDescription = "spoon"
                        )

                        Text(
                            text = stringResource(R.string.take_spoon),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DeckCardInGameTable(
    title: String,
    size: Int,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title
        )

        Box(
            modifier = Modifier
                .size(width = 35.dp, height = 60.dp)
                .border(width = 2.dp, color = Color.Black),
            contentAlignment = Alignment.Center
        ) {
            if (size != 0) {
                Image(
                    painter = painterResource(R.drawable.back_dark),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }

        Text(
            text = size.toString()
        )
    }
}