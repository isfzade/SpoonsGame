package az.isfan.spoonsgame.screens.scores.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.isfan.spoonsgame.R
import az.isfan.spoonsgame.data.models.GameData
import az.isfan.spoonsgame.screens.common.TopBarScreen

@Composable
@ExperimentalMaterial3Api
fun ScoresContent(
    games: List<GameData>,
    onBackButtonClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxWidth(),
        topBar = {
            TopBarScreen(
                title = stringResource(R.string.high_scores),
                showBackButton = true,
                onBackButtonClick = onBackButtonClick
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .padding(top = 10.dp)
                .fillMaxWidth()
        ) {
            ItemsInScores(games)
        }
    }
}