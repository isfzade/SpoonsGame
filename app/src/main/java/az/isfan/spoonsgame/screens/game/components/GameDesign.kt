package az.isfan.spoonsgame.screens.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import az.isfan.spoonsgame.data.enums.ChairEnum
import az.isfan.spoonsgame.data.enums.GameStatusEnum
import az.isfan.spoonsgame.data.models.CardData
import az.isfan.spoonsgame.data.models.PlayerData
import az.isfan.spoonsgame.screens.game.components.BottomChair

@Composable
fun GameDesign(
    players: List<PlayerData>,
    availableDeckCards: List<CardData>,
    discardedDeckCards: List<CardData>,
    showTakeSpoonButton: Boolean,
    gameStatus: GameStatusEnum,
    onCardClick: (card: CardData) -> Unit,
    onSpoonButtonClick: () -> Unit,
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
            TopChairs(
                players = players.filter { it.chair == ChairEnum.TOP_LEFT || it.chair == ChairEnum.TOP_RIGHT }
            )
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
                    LeftChair(
                        players = players.filter { it.chair == ChairEnum.LEFT }
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxSize()
                        .background(color = Color.Green.copy(alpha=0.4f)),
                ) {
                    GameTable(
                        showTakeSpoonButton = showTakeSpoonButton,
                        gameStatus = gameStatus,
                        onSpoonButtonClick = onSpoonButtonClick,
                        availableCards = availableDeckCards,
                        discardedCards = discardedDeckCards
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    RightChair(
                        players = players.filter { it.chair == ChairEnum.RIGHT }
                    )
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