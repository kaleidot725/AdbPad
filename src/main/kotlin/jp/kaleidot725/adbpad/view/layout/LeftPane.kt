package jp.kaleidot725.adbpad.view.component.menulist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
        )

        Text(
            text = "メニュー",
            style = MaterialTheme.typography.subtitle2
        )

        MenuList(
            menus = listOf("コマンド実行", "スクリーンショット", "自動入力補助"),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
        )
    }
}