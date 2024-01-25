package az.isfan.spoonsgame.Screens.Game

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import az.isfan.spoonsgame.ViewModels.GameViewModel

@Composable
fun GameScreen(
    playerCount: Int,
    navController: NavHostController,
    gameViewModel: GameViewModel = hiltViewModel()
) {

}