package az.isfan.spoonsgame.screens.scores

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import az.isfan.spoonsgame.general.Cavab
import az.isfan.spoonsgame.screens.Scores.components.ScoresContent
import az.isfan.spoonsgame.viewmodels.ScoresViewModel

@Composable
@ExperimentalMaterial3Api
fun ScoresScreen(
    navController: NavHostController,
    scoresViewModel: ScoresViewModel = hiltViewModel(),
) {
    val games by scoresViewModel.games.collectAsStateWithLifecycle()

    when (games) {
        is Cavab.Success -> {
            ScoresContent(
                games = (games as Cavab.Success).data,
                onBackButtonClick = {
                    navController.navigateUp()
                }
            )
        }
        is Cavab.Loading -> CircularProgressIndicator()
        else -> Unit
    }
}