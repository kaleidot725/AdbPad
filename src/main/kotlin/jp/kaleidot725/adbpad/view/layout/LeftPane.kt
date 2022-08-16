package jp.kaleidot725.adbpad.view.component.menulist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.view.component.extension.clickableNoRipple

@Composable
fun LeftPane(modifier: Modifier = Modifier) {
    val devices by remember { mutableStateOf(listOf("端末A", "端末B", "端末C")) }
    var selectedDevice by remember { mutableStateOf(devices.first()) }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        Text(
            text = "ターゲット端末",
            style = MaterialTheme.typography.subtitle2
        )

        DeviceList(
            devices = devices,
            selectedDevice = selectedDevice,
            onSelectDevice = { selectedDevice = it },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "ツール",
            style = MaterialTheme.typography.subtitle2
        )

        MenuList(
            menus = listOf("コマンド実行", "スクリーンショット", "自動入力補助"),
            modifier = Modifier.fillMaxWidth().weight(weight = 0.9f, fill = true)
        )

        MenuItem(
            icon = Icons.Default.Settings,
            iconDescription = "Setting Icon",
            text = "設定",
            modifier = Modifier.clickableNoRipple { /** TODO */ }
        )
    }
}
