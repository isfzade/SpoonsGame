package az.isfan.spoonsgame.navigation

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import az.isfan.spoonsgame.data.enums.RouteEnum
import az.isfan.spoonsgame.screens.Game.GameScreen
import az.isfan.spoonsgame.screens.Menu.MenuScreen
import az.isfan.spoonsgame.screens.Scores.ScoresScreen

@Composable
@ExperimentalMaterial3Api
fun NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    val TAG = "isf_NavGraph"
    NavHost(navController = navController, startDestination = RouteEnum.MENU.name) {
        composable(route = RouteEnum.MENU.name) {
            Log.i(TAG, "NavGraph: route=${RouteEnum.MENU}")
            MenuScreen(
                navController = navController
            )
        }

        composable(route = RouteEnum.THREE_PLAYERS.name) {
            Log.i(TAG, "NavGraph: route=${RouteEnum.THREE_PLAYERS}")
            GameScreen(
                playerCount = 3,
                navController = navController
            )
        }

        composable(route = RouteEnum.FOUR_PLAYERS.name) {
            Log.i(TAG, "NavGraph: route=${RouteEnum.FOUR_PLAYERS}")
            GameScreen(
                playerCount = 4,
                navController = navController
            )
        }

        composable(route = RouteEnum.FIVE_PLAYERS.name) {
            Log.i(TAG, "NavGraph: route=${RouteEnum.FIVE_PLAYERS}")
            GameScreen(
                playerCount = 5,
                navController = navController
            )
        }

        composable(route = RouteEnum.HIGH_SCORES.name) {
            Log.i(TAG, "NavGraph: route=${RouteEnum.HIGH_SCORES}")
            ScoresScreen(
                navController = navController
            )
        }

        composable(route = RouteEnum.LAST_GAME.name) {
            Log.i(TAG, "NavGraph: route=${RouteEnum.LAST_GAME}")
            GameScreen(
                navController = navController
            )
        }
    }
}