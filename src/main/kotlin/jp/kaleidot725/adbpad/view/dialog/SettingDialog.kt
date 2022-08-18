package jp.kaleidot725.adbpad.view.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.view.extension.consumeClickEvent

@Composable
fun SettingDialog(onClose: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .consumeClickEvent()
            .background(Color.Black.copy(alpha = 0.4f))
            .padding(64.dp)
    ) {
        Text(text = "Close", modifier = Modifier.clickable { onClose() })
    }
}
