package jp.kaleidot725.adbpad.view.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.Language
import jp.kaleidot725.adbpad.view.component.setting.SettingField
import jp.kaleidot725.adbpad.view.component.setting.SettingHeader
import jp.kaleidot725.adbpad.view.component.setting.SettingTitle

@Composable
fun SettingScreen(
    adbDirectoryPath: String,
    onChangeAdbDirectoryPath: (String) -> Unit,
    adbPortNumberPath: String,
    onChangeAdbPortNumberPath: (String) -> Unit,
    sdkAndroidDirectoryPath: String,
    onChangeSdkAndroidDirectoryPath: (String) -> Unit,
    onSave: () -> Unit,
    onCancel: () -> Unit,
    onClose: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray.copy(alpha = 0.5f))) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxSize().padding(32.dp)
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    SettingHeader(
                        onClose = onClose,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Divider(modifier = Modifier.fillMaxWidth())

                    SettingTitle(
                        text = Language.SETTING_ADB_HEADER,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )

                    SettingField(
                        title = Language.SETTING_ADB_DIRECTORY_PATH_TITLE,
                        input = adbDirectoryPath,
                        onValueChange = onChangeAdbDirectoryPath
                    )

                    SettingField(
                        title = Language.SETTING_ADB_PORT_NUMBER_TITLE,
                        input = adbPortNumberPath,
                        onValueChange = onChangeAdbPortNumberPath
                    )

                    SettingTitle(
                        text = Language.SETTING_ANDROID_SDK_HEADER,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )

                    SettingField(
                        title = Language.SETTING_ANDROID_SDK_DIRECTORY_PATH_TITLE,
                        input = sdkAndroidDirectoryPath,
                        onValueChange = onChangeSdkAndroidDirectoryPath
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Button(onClick = {}) {
                        Text(text = Language.CANCEL, modifier = Modifier.width(75.dp), textAlign = TextAlign.Center)
                    }
                    Button(onClick = {}) {
                        Text(text = Language.SAVE, modifier = Modifier.width(75.dp), textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}
