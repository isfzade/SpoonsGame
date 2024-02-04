package az.isfan.spoonsgame.screens.game.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import az.isfan.spoonsgame.data.enums.GameStatusEnum
import az.isfan.spoonsgame.data.models.CardData
import az.isfan.spoonsgame.data.models.PlayerData
import az.isfan.spoonsgame.screens.common.TopBarScreen

@Composable
@ExperimentalMaterial3Api
fun GameContent(
    title: String,
    players: List<PlayerData>,
    availableDeckCards: List<CardData>,
    discardedDeckCards: List<CardData>,
    showTakeSpoonButton: Boolean,
    gameStatus: GameStatusEnum,
    onBackButtonClick: () -> Unit,
    onCardClick: (card: CardData) -> Unit,
    onSpoonButtonClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarScreen(
                title = title,
                showBackButton = true,
                onBackButtonClick = onBackButtonClick
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            GameDesign(
                players = players,
                availableDeckCards = availableDeckCards,
                discardedDeckCards = discardedDeckCards,
                showTakeSpoonButton = showTakeSpoonButton,
                gameStatus = gameStatus,
                onCardClick = onCardClick,
                onSpoonButtonClick = onSpoonButtonClick,
            )
        }
    }
}