package jp.kaleidot725.adbpad.view.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SettingCommandScreen(onClose: () -> Unit) {
    Box(Modifier.background(Color.DarkGray.copy(alpha = 0.5f))) {
        Card(Modifier.fillMaxSize().padding(32.dp)) {
            Button(onClick = onClose) {
                Text("Close")
            }
        }
    }
}
