package az.isfan.spoonsgame.screens.game.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import az.isfan.spoonsgame.data.models.PlayerData

@Composable
fun SideChair(
    players: List<PlayerData>
) {
    if (players.isNotEmpty()) {
        val player = players.first()

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 5.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            PlayerInfo(
                name = player.name,
                playTurn = player.playTurn,
                letterSize = player.lettersSize,
                mood = player.mood
            )

            if (!player.kicked) {
                SideBotCards(
                    playTurn = player.playTurn,
                    cards = player.cards,
                )
            }
        }
    }
}