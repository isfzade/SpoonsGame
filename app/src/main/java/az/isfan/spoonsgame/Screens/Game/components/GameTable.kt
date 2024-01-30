package az.isfan.spoonsgame.Screens.Game.components

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
import androidx.compose.material3.Button
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
    onShowSpoonButtonClick: () -> Unit,
    showTakeSpoonButton: Boolean,
    availableCards: List<CardData>,
    discardedCards: List<CardData>
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .weight(2f)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
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

        if (showTakeSpoonButton) {
            Button(
                onClick = onShowSpoonButtonClick,
                modifier = Modifier
                    .background(color=Color.Red)
                    .fillMaxWidth(),
            ) {
                Text(stringResource(R.string.take_spoon))
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