package az.isfan.spoonsgame.Screens.Game.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
    val isPlaying = player.isPlaying.collectAsStateWithLifecycle().value
    val playTurn = player.playTurn.collectAsStateWithLifecycle().value
    val letterSize = player.lettersCollected.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isPlaying) {
            Box(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                TopBotCards(
                    playTurn = playTurn,
                    cards = cards,
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
                playTurn = playTurn,
                letterSize = letterSize,
            )
        }
    }
}