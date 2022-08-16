package jp.kaleidot725.adbpad.view.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ScreenShotPane() {
    Box(Modifier.fillMaxSize()) {
        Text("ScreenShotPane", Modifier.align(Alignment.Center))
    }
}
