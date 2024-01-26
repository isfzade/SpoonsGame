package az.isfan.spoonsgame.Screens.Game.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GameDesign() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {

        }

        Box(
            modifier = Modifier
                .weight(5f)
                .fillMaxSize()
        ) {

        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {

        }
    }
}