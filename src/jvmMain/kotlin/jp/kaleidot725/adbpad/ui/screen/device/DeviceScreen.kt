package jp.kaleidot725.adbpad.ui.screen.device

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.ui.component.button.FloatingDialog

@Composable
fun DeviceScreen(onClose: () -> Unit) {
    FloatingDialog(
        modifier =
            Modifier
                .width(960.dp)
                .fillMaxHeight()
                .padding(vertical = 32.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("TEST")
            Button(
                onClick = onClose,
            ) {
                Text("CLOSE")
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DeviceScreen(
        onClose = {},
    )
}
