package az.isfan.spoonsgame.Screens.Game.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import az.isfan.spoonsgame.Data.Enums.ChairEnum
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.General.Constants
import coil.compose.AsyncImage

@Composable
fun PlayerName(
    name: String,
    chair: ChairEnum
) {
    Text(
        text = name,
        modifier = Modifier
            .rotate(
                when (chair) {
                    ChairEnum.LEFT -> 90f
                    ChairEnum.RIGHT -> -90f
                    else -> 0f
                }
            )
    )
}

@Composable
fun SideBotCards(
    cards: List<CardData>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            space = 5.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        cards.forEach{
            AsyncImage(
                model = Constants.CARD_IMAGE_LINKS["back"],
                contentDescription = null,
                modifier = Modifier
                    .rotate(90f)
                    .size(height = 35.dp, width = 60.dp)
                    .border(width = 1.dp, color = Color.Black)
            )
        }
    }
}

@Composable
fun TopBotCards(
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
            AsyncImage(
                model = Constants.CARD_IMAGE_LINKS["back"],
                contentDescription = null,
                modifier = Modifier
                    .size(height = 60.dp, width = 35.dp)
                    .border(width = 1.dp, color = Color.Black)
            )
        }
    }
}