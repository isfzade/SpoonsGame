package az.isfan.spoonsgame.screens.game.components

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import az.isfan.spoonsgame.data.models.CardData
import az.isfan.spoonsgame.data.models.PlayerData
import az.isfan.spoonsgame.general.getCardImageResource

@Composable
fun BottomChair(
    player: PlayerData,
    onCardClick: (card: CardData) -> Unit,
) {
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
            PlayerInfo(
                name = player.name,
                playTurn = player.playTurn,
                letterSize = player.lettersSize,
            )
        }

        if (!player.kicked) {
            Box(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LocalPlayerCards(
                    playTurn = player.playTurn,
                    cards = player.cards,
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
                    .size(width = 60.dp, height = 84.dp)
                    .border(
                        width = if (playTurn) 2.dp else 1.dp,
                        color = if (playTurn) Color.Blue else Color.Black
                    )
                    .clickable {
                        if (playTurn) onCardClick(card)
                    }
            )
        }
    }
}