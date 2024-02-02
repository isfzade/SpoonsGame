package az.isfan.spoonsgame.Screens.Menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import az.isfan.spoonsgame.Data.Enums.RouteEnum
import az.isfan.spoonsgame.R
import az.isfan.spoonsgame.Screens.Menu.components.MenuContent

@Composable
@ExperimentalMaterial3Api
fun MenuScreen(
    navController: NavHostController
) {
    Scaffold(
        modifier = Modifier
            .fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name)
                    )
                },
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
        ) {
            MenuContent(
                onThreePlayersButtonClick = {
                    navController.navigate(RouteEnum.THREE_PLAYERS.name)
                },
                onFourPlayersButtonClick = {
                    navController.navigate(RouteEnum.FOUR_PLAYERS.name)
                },
                onFivePlayersButtonClick = {
                    navController.navigate(RouteEnum.FIVE_PLAYERS.name)
                },
                onHighScoresButtonClick = {
                    navController.navigate(RouteEnum.HIGH_SCORES.name)
                },
                onLoadLastGameButtonClick = {
                    navController.navigate(RouteEnum.LAST_GAME.name)
                }
            )
        }
    }
}