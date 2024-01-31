package az.isfan.spoonsgame.Screens.Game.components

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.Data.Models.GameData
import az.isfan.spoonsgame.Data.Models.PlayerData

@Composable
@ExperimentalMaterial3Api
fun GameContent(
    game: GameData,
    title: String,
    showTakeSpoonButton: Boolean,
    showGiveLetterButton: Boolean,
    onBackButtonClick: () -> Unit,
    onCardClick: (card: CardData) -> Unit,
    onSpoonButtonClick: () -> Unit,
    onGiveLetterButtonClick: (player: PlayerData) -> Unit,
) {
    val players = game.players.collectAsStateWithLifecycle().value
    val availableDeckCards = game.availableDeckCards.collectAsStateWithLifecycle().value
    val discardedDeckCards = game.discardedDeckCards.collectAsStateWithLifecycle().value

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
                showGiveLetterButton = showGiveLetterButton,
                showTakeSpoonButton = showTakeSpoonButton,
                onCardClick = onCardClick,
                onSpoonButtonClick = onSpoonButtonClick,
                onGiveLetterButtonClick = onGiveLetterButtonClick,
            )
        }
    }
}