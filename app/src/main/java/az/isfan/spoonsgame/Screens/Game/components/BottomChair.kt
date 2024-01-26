package az.isfan.spoonsgame.Screens.Game.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import az.isfan.spoonsgame.Data.Enums.ChairEnum
import az.isfan.spoonsgame.Data.Models.PlayerData

@Composable
fun BottomChair(
    player: PlayerData
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PlayerName(player.name, player.chair)
    }
}

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
                    ChairEnum.LEFT -> -90f
                    ChairEnum.RIGHT -> 90f
                    else -> 0f
                }
            )
    )
}