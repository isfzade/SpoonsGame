package az.isfan.spoonsgame.screens.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.isfan.spoonsgame.R
import az.isfan.spoonsgame.ui.theme.TopBarColor

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Text(
            text = stringResource(R.string.loading),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
        )

//        LinearProgressIndicator(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 20.dp, end = 20.dp),
//            color = TopBarColor,
//            trackColor = Color.Transparent
//        )
    }
}