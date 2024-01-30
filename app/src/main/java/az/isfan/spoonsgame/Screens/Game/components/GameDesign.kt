package az.isfan.spoonsgame.Screens.Game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import az.isfan.spoonsgame.Data.Enums.ChairEnum
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.Data.Models.PlayerData

@Composable
fun GameDesign(
    showTakeSpoonButton: Boolean,
    players: List<PlayerData>,
    availableDeckCards: List<CardData>,
    discardedDeckCards: List<CardData>,
    onShowSpoonButtonClick: () -> Unit,
    onCardClick: (card: CardData) -> Unit,
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
                .weight(3f)
                .fillMaxSize(),
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
                    LeftChair(players = players.filter { it.chair == ChairEnum.LEFT })
                }

                Box(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxSize()
                        .background(color = Color.Green.copy(alpha=0.4f)),
                ) {
                    GameTable(
                        onShowSpoonButtonClick = onShowSpoonButtonClick,
                        showTakeSpoonButton = showTakeSpoonButton,
                        availableCards = availableDeckCards,
                        discardedCards = discardedDeckCards
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    RightChair(players = players.filter { it.chair == ChairEnum.RIGHT })
                }
            }
        }

        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxSize()
        ) {
            BottomChair(
                player = players.first { it.chair == ChairEnum.BOTTOM },
                onCardClick = onCardClick,
            )
        }
    }
}