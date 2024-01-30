package az.isfan.spoonsgame.Screens.Game

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
    val players = viewModel.players.collectAsStateWithLifecycle().value
    val availableDeckCards = viewModel.availableDeckCards.collectAsStateWithLifecycle().value
    val discardedDeckCards = viewModel.discardedDeckCards.collectAsStateWithLifecycle().value
    val showTakeSpoonButton = viewModel.showTakeSpoon.collectAsStateWithLifecycle().value

    when (players) {
        is Cavab.Success -> {
            GameContent(
                title = title,
                showTakeSpoonButton = showTakeSpoonButton,
                players = players.data,
                availableDeckCards = availableDeckCards,
                discardedDeckCards = discardedDeckCards,
                onBackButtonClick = {
                    navController.navigateUp()
                },
                onCardClick = { card ->
                    viewModel.discardCard(card)
                },
                onShowSpoonButtonClick = {
                    viewModel.setTookSpoon(true)
                }
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
        viewModel.setupGame(playerCount)
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