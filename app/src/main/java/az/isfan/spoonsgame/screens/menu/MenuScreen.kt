package az.isfan.spoonsgame.screens.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FrontHand
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import az.isfan.spoonsgame.R
import az.isfan.spoonsgame.data.enums.RouteEnum
import az.isfan.spoonsgame.screens.common.TopBarScreen
import az.isfan.spoonsgame.screens.menu.components.MenuContent

@Composable
@ExperimentalMaterial3Api
fun MenuScreen(
    navController: NavHostController
) {
    Scaffold(
        modifier = Modifier
            .fillMaxWidth(),
        topBar = {
            TopBarScreen(
                title = stringResource(R.string.app_name),
                icon = Icons.Default.FrontHand,
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .padding(top=50.dp)
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