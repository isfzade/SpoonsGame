package az.isfan.spoonsgame.Screens.Game.components

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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import az.isfan.spoonsgame.Data.Enums.ChairEnum
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.Data.Models.PlayerData
import coil.compose.AsyncImage

@Composable
fun BottomChair(
    player: PlayerData
) {
    val cards = player.cards.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PlayerName(player.name, player.chair)

        LocalPlayerCards(cards)
    }
}

@Composable
fun LocalPlayerCards(
    cards: List<CardData>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        cards.forEach{ card ->
            AsyncImage(
                model = card.imageLink,
                contentDescription = null,
                modifier = Modifier
                    .size(height = 100.dp, width = 60.dp)
            )
        }
    }
}