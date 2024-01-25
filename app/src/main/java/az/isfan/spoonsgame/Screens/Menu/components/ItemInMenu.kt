package az.isfan.spoonsgame.Screens.Menu.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ItemInMenu(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick
    ) {
        Text(
            text = text,
        )
    }
}