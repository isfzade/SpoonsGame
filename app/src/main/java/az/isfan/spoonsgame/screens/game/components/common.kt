package az.isfan.spoonsgame.screens.game.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.isfan.spoonsgame.R
import az.isfan.spoonsgame.data.enums.MoodEnum
import az.isfan.spoonsgame.data.models.CardData
import az.isfan.spoonsgame.general.Constants
import az.isfan.spoonsgame.general.getMoodEmojiResource

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
        repeat(cards.size) {
            Image(
                painter = painterResource(R.drawable.back_rot),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 49.dp, height = 35.dp)
                    .border(
                        width = if (playTurn) 2.dp else 1.dp,
                        color = if (playTurn) Color.Blue else Color.Black
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
        repeat(cards.size) {
            Image(
                painter = painterResource(R.drawable.back),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 35.dp, height = 49.dp)
                    .border(
                        width = if (playTurn) 2.dp else 1.dp,
                        color = if (playTurn) Color.Blue else Color.Black
                    )
            )
        }
    }
}

@Composable
fun PlayerInfo(
    name: String,
    playTurn: Boolean,
    letterSize: Int,
    mood: MoodEnum,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 5.dp
            )
        ) {
            Text(
                text = stringResource(getMoodEmojiResource(mood)),
                fontSize = 22.sp
            )

            Text(
                text = name,
                fontWeight = if (playTurn) FontWeight.Bold else FontWeight.Normal,
                color = if (playTurn) Color.Blue else Color.Black,
                fontSize = 18.sp
            )
        }

        Text(
            text = Constants.SPOON.slice(IntRange(0, letterSize-1)),
            fontWeight = if (playTurn) FontWeight.Bold else FontWeight.Normal,
            color = if (playTurn) Color.Blue else Color.Black,
            fontSize = 20.sp
        )
    }

}