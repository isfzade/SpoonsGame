package az.isfan.spoonsgame.Screens.Game.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import az.isfan.spoonsgame.Data.Enums.ChairEnum
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.Data.Models.PlayerData
import az.isfan.spoonsgame.General.getCardImageResource
import az.isfan.spoonsgame.R
import coil.compose.AsyncImage

@Composable
fun BottomChair(
    player: PlayerData,
    onCardClick: (card: CardData) -> Unit,
) {
    val cards = player.cards.collectAsStateWithLifecycle().value
    val isPlaying = player.isPlaying.collectAsStateWithLifecycle().value
    val playTurn = player.playTurn.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = player.name,
                fontWeight = if (playTurn) FontWeight.Bold else FontWeight.Normal,
                color = if (playTurn) Color.Blue else Color.Black
            )
        }

        if (isPlaying) {
            Box(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LocalPlayerCards(
                    playTurn = playTurn,
                    cards = cards,
                    onCardClick = onCardClick
                )
            }
        }
    }
}

@Composable
fun LocalPlayerCards(
    playTurn: Boolean,
    cards: List<CardData>,
    onCardClick: (card: CardData) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            space = 5.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        cards.forEach{ card ->
            Image(
                painter = painterResource(getCardImageResource(card.rank, card.suit)),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 60.dp, height = 100.dp)
                    .border(
                        width = if (playTurn) 2.dp else 1.dp,
                        color = if (playTurn) Color.Blue else Color.Black
                    )
                    .clickable {
                        onCardClick(card)
                    }
            )
        }
    }
}