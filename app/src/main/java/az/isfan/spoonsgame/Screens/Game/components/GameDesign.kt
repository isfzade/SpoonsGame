package az.isfan.spoonsgame.Screens.Game.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import az.isfan.spoonsgame.Data.Enums.ChairEnum
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.Data.Models.PlayerData

@Composable
fun GameDesign(
    players: List<PlayerData>,
    availableDeckCards: List<CardData>,
    discardedDeckCards: List<CardData>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            TopChairs(players = players.filter { it.chair == ChairEnum.TOP_LEFT || it.chair == ChairEnum.TOP_RIGHT })
        }

        Box(
            modifier = Modifier
                .weight(5f)
                .fillMaxSize()
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
                    LeftChair(player = players.first { it.chair == ChairEnum.LEFT })
                }

                Box(
                    modifier = Modifier
                        .weight(3f)
                        .fillMaxSize()
                ) {
                    GameTable(
                        availableCards = availableDeckCards,
                        discardedCards = discardedDeckCards
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    RightChair(player = players.first { it.chair == ChairEnum.RIGHT })
                }
            }
        }

        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxSize()
        ) {
            BottomChair(player = players.first { it.chair == ChairEnum.BOTTOM })
        }
    }
}