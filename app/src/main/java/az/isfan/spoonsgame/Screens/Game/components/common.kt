package az.isfan.spoonsgame.Screens.Game.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import az.isfan.spoonsgame.Data.Enums.ChairEnum
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.General.Constants
import az.isfan.spoonsgame.R
import coil.compose.AsyncImage

@Composable
fun SideBotCards(
    playTurn: Boolean,
    cards: List<CardData>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            space = 2.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        cards.forEach{
            Image(
                painter = painterResource(R.drawable.back_dark),
                contentDescription = null,
                modifier = Modifier
                    .rotate(90f)
                    .then(
                        Modifier
                            .size(width = 60.dp, height = 35.dp)
                            .border(if (playTurn) 1.dp else 0.dp, color  = if (playTurn) Color.Blue else Color.Transparent)
                    )
            )
        }
    }
}

@Composable
fun TopBotCards(
    playTurn: Boolean,
    cards: List<CardData>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            space = 2.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        cards.forEach{
            Image(
                painter = painterResource(R.drawable.back_dark),
                contentDescription = null,
                modifier = Modifier
                    .size(height = 60.dp, width = 35.dp)
                    .border(if (playTurn) 1.dp else 0.dp, color  = if (playTurn) Color.Blue else Color.Transparent)
            )
        }
    }
}