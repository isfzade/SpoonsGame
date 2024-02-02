package az.isfan.spoonsgame.screens.Scores.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import az.isfan.spoonsgame.data.models.GameData

@Composable
fun ItemsInScores(
    games: List<GameData>
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        games.forEach { game ->
            item {
                ItemInScores(game = game)
            }
        }
    }
}