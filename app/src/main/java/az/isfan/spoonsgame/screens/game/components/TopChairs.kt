package az.isfan.spoonsgame.screens.game.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import az.isfan.spoonsgame.data.enums.ChairEnum
import az.isfan.spoonsgame.data.models.PlayerData
import az.isfan.spoonsgame.screens.game.components.PlayerInfo
import az.isfan.spoonsgame.screens.game.components.TopBotCards

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
                TopChair(
                    player = players.first { it.chair == ChairEnum.TOP_LEFT },
                )
            }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            if (players.any { it.chair == ChairEnum.TOP_RIGHT }) {
                TopChair(
                    player = players.first { it.chair == ChairEnum.TOP_RIGHT }
                )
            }
        }
    }
}

@Composable
fun TopChair(
    player: PlayerData,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!player.kicked) {
            Box(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                TopBotCards(
                    playTurn = player.playTurn,
                    cards = player.cards,
                )
            }
        }

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
    }
}