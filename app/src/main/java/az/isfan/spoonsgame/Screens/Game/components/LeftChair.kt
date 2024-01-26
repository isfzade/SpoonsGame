package az.isfan.spoonsgame.Screens.Game.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import az.isfan.spoonsgame.Data.Models.PlayerData

@Composable
fun LeftChair(
    player: PlayerData
) {
    val cards = player.cards.collectAsStateWithLifecycle().value

    Row(
        modifier = Modifier
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SideBotCards(cards)

        PlayerName(player.name, player.chair)
    }
}