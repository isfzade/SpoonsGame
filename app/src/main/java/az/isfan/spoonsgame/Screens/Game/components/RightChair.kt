package az.isfan.spoonsgame.Screens.Game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import az.isfan.spoonsgame.Data.Models.PlayerData
import az.isfan.spoonsgame.R

@Composable
fun RightChair(
    players: List<PlayerData>
) {
    if (players.isNotEmpty()) {
        val player = players.first()

        Row(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
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
            }

            if (!player.kicked) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    SideBotCards(
                        playTurn = player.playTurn,
                        cards = player.cards
                    )
                }
            }

        }
    }
}