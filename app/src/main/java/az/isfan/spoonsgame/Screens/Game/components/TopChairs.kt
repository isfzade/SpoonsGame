package az.isfan.spoonsgame.Screens.Game.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import az.isfan.spoonsgame.Data.Enums.ChairEnum
import az.isfan.spoonsgame.Data.Models.PlayerData

@Composable
fun TopChairs(
    players: List<PlayerData>
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            if (players.any { it.chair == ChairEnum.TOP_LEFT }) {
                TopChair(player = players.first { it.chair == ChairEnum.TOP_LEFT })
            }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            if (players.any { it.chair == ChairEnum.TOP_RIGHT }) {
                TopChair(player = players.first { it.chair == ChairEnum.TOP_RIGHT })
            }
        }
    }
}

@Composable
fun TopChair(
    player: PlayerData
) {
    val cards = player.cards.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBotCards(cards)

        PlayerName(player.name, player.chair)
    }
}