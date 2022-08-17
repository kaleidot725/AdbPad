package jp.kaleidot725.adbpad.view.component.menu

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
import jp.kaleidot725.adbpad.model.Menu
import jp.kaleidot725.adbpad.view.component.extension.clickableNoRipple

@Composable
fun MenuPane(
    devices: List<String>,
    selectedDevice: String,
    onSelectDevice: (String) -> Unit,
    menus: List<Menu>,
    selectedMenu: Menu,
    onSelectMenu: (Menu) -> Unit,
    onOpenSetting: () -> Unit,
    modifier: Modifier = Modifier
) {
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
            onSelectDevice = onSelectDevice,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "ツール",
            style = MaterialTheme.typography.subtitle2
        )

        MenuList(
            menus = menus,
            selectedMenu = selectedMenu,
            onSelectMenu = onSelectMenu,
            modifier = Modifier.fillMaxWidth().weight(weight = 0.9f, fill = true)
        )

        MenuItem(
            icon = Icons.Default.Settings,
            iconDescription = "Setting Icon",
            text = "設定",
            modifier = Modifier.clickableNoRipple { onOpenSetting() }
        )
    }
}
