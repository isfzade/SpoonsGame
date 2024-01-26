package az.isfan.spoonsgame.Screens.Game.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import az.isfan.spoonsgame.Data.Models.PlayerData

@Composable
fun RightChair(
    player: PlayerData
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlayerName(player.name, player.chair)
    }
}