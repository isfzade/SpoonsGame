package az.isfan.spoonsgame.Screens.Scores

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import az.isfan.spoonsgame.General.Cavab
import az.isfan.spoonsgame.R
import az.isfan.spoonsgame.Screens.Scores.components.ScoresContent
import az.isfan.spoonsgame.ViewModels.ScoresViewModel

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