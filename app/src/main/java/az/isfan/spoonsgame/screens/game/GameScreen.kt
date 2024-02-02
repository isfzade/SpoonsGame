package az.isfan.spoonsgame.screens.game

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import az.isfan.spoonsgame.general.Cavab
import az.isfan.spoonsgame.R
import az.isfan.spoonsgame.screens.Game.components.GameContent
import az.isfan.spoonsgame.viewmodels.GameViewModel

@Composable
@ExperimentalMaterial3Api
fun GameScreen(
    playerCount: Int,
    navController: NavHostController,
    gameViewModel: GameViewModel = hiltViewModel()
) {
    GameSetupLogic(
        title = when (playerCount) {
            3 -> stringResource(R.string.three_players)
            4 -> stringResource(R.string.four_players)
            else -> stringResource(R.string.five_players)
        },
        navController = navController,
        viewModel = gameViewModel
    )

    SetupNewGame(
        playerCount = playerCount,
        viewModel = gameViewModel
    )
}

@Composable
@ExperimentalMaterial3Api
fun GameScreen(
    navController: NavHostController,
    gameViewModel: GameViewModel = hiltViewModel()
) {
    GameSetupLogic(
        title = stringResource(R.string.load_last_game),
        navController = navController,
        viewModel = gameViewModel
    )

    LoadLastGame(
        viewModel = gameViewModel
    )
}

@Composable
@ExperimentalMaterial3Api
fun GameSetupLogic(
    title: String,
    viewModel: GameViewModel,
    navController: NavHostController,
) {
    val isReady by viewModel.isGameReady.collectAsStateWithLifecycle()
    val players by viewModel.players.collectAsStateWithLifecycle()
    val availableDeckCards by viewModel.availableDeckCards.collectAsStateWithLifecycle()
    val discardedDeckCards by viewModel.discardedDeckCards.collectAsStateWithLifecycle()
    val showTakeSpoonButton by viewModel.showTakeSpoonButton.collectAsStateWithLifecycle()
    val gameStatus by viewModel.status.collectAsStateWithLifecycle()

    when (isReady) {
        is Cavab.Success -> {
            GameContent(
                title = title,
                players = players,
                availableDeckCards = availableDeckCards,
                discardedDeckCards = discardedDeckCards,
                showTakeSpoonButton = showTakeSpoonButton,
                gameStatus = gameStatus,
                onBackButtonClick = {
                    navController.navigateUp()
                },
                onCardClick = { card ->
                    viewModel.localSelectsCard(card)
                },
                onSpoonButtonClick = {
                    viewModel.spoonButtonClicked()
                },
            )
        }
        is Cavab.Loading -> CircularProgressIndicator()
        else -> Unit
    }

    SaveGame(viewModel = viewModel)
}

@Composable
fun SetupNewGame(
    playerCount: Int,
    viewModel: GameViewModel
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.setupNewGame(playerCount)
    }
}

@Composable
fun LoadLastGame(
    viewModel: GameViewModel
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.loadLastGame()
    }
}

@Composable
fun SaveGame(
    viewModel: GameViewModel
) {
    DisposableEffect(key1 = Unit) {
        onDispose {
            viewModel.save()
        }
    }
}