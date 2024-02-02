package az.isfan.spoonsgame.screens.game.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import az.isfan.spoonsgame.data.enums.GameStatusEnum
import az.isfan.spoonsgame.data.models.CardData
import az.isfan.spoonsgame.data.models.PlayerData

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
            TopAppBar(
                title = {
                    Text(
                        text = title
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackButtonClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "backButton"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
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