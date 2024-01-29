package az.isfan.spoonsgame.Screens.Game

import android.widget.ProgressBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import az.isfan.spoonsgame.General.Cavab
import az.isfan.spoonsgame.R
import az.isfan.spoonsgame.Screens.Game.components.GameContent
import az.isfan.spoonsgame.ViewModels.GameViewModel

@Composable
@ExperimentalMaterial3Api
fun GameScreen(
    playerCount: Int,
    navController: NavHostController,
    gameViewModel: GameViewModel = hiltViewModel()
) {
    val players = gameViewModel.players.collectAsStateWithLifecycle().value
    val availableDeckCards = gameViewModel.availableDeckCards.collectAsStateWithLifecycle().value
    val discardedDeckCards = gameViewModel.discardedDeckCards.collectAsStateWithLifecycle().value

    when (players) {
        is Cavab.Success -> {
            GameContent(
                title = when (playerCount) {
                    3 -> stringResource(R.string.three_players)
                    4 -> stringResource(R.string.four_players)
                    else -> stringResource(R.string.five_players)
                },
                players = players.data,
                availableDeckCards = availableDeckCards,
                discardedDeckCards = discardedDeckCards,
                onBackButtonClick = {
                    navController.navigateUp()
                },
                onCardClick = { card ->
                    gameViewModel.discardCard(card)
                }
            )
        }
        is Cavab.Loading -> CircularProgressIndicator()
        else -> Unit
    }

    GetPlayersInGameScreen(
        playerCount = playerCount,
        viewModel = gameViewModel
    )
}

@Composable
fun GetPlayersInGameScreen(
    playerCount: Int,
    viewModel: GameViewModel
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.setupGame(playerCount)
    }
}