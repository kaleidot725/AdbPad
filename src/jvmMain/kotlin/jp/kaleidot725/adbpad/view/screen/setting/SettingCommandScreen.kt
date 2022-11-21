package jp.kaleidot725.adbpad.view.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.view.component.setting.SettingField
import jp.kaleidot725.adbpad.view.component.setting.SettingHeader
import jp.kaleidot725.adbpad.view.component.setting.SettingTitle

@Composable
fun SettingCommandScreen(onClose: () -> Unit) {
    Box(modifier = Modifier.background(Color.DarkGray.copy(alpha = 0.5f))) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxSize().padding(32.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                SettingHeader(
                    onClose = onClose,
                    modifier = Modifier.fillMaxWidth()
                )

                Divider(modifier = Modifier.fillMaxWidth())

                SettingTitle(
                    text = "ADB & Android SDK",
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

                SettingField(
                    title = "ADB PATH",
                    input = "ADB SAMPLE PATH",
                    onValueChange = {}
                )

                SettingField(
                    title = "ANDROID SDK PATH",
                    input = "ANDROID SDK SAMPLE PATH",
                    onValueChange = {}
                )

                SettingField(
                    title = "PORT NUMBER",
                    input = "PORT NUMBER SAMPLE PATH",
                    onValueChange = {}
                )

                Button(onClick = onClose) {
                    Text("CLOSE")
                }
            }
        }
    }
}
