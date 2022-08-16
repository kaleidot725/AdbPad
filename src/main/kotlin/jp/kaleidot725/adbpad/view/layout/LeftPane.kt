package jp.kaleidot725.adbpad.view.component.menulist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.view.component.extension.clickableNoRipple

@Composable
fun LeftPane(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        Text(
            text = "ターゲット端末",
            style = MaterialTheme.typography.subtitle2
        )

        DeviceList(
            devices = listOf("端末A", "端末B", "端末C"),
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