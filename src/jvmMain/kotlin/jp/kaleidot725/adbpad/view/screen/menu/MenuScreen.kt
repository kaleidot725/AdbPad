package jp.kaleidot725.adbpad.view.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.Menu
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.view.common.extension.clickableNoRipple
import jp.kaleidot725.adbpad.view.component.menu.DropDownDeviceMenu
import jp.kaleidot725.adbpad.view.component.menu.MenuItem
import jp.kaleidot725.adbpad.view.component.menu.MenuList
import jp.kaleidot725.adbpad.view.model.resource.Language

@Composable
fun MenuScreen(
    devices: List<Device>,
    selectedDevice: Device?,
    onSelectDevice: (Device) -> Unit,
    menus: List<Menu>,
    selectedMenu: Menu?,
    onSelectMenu: (Menu) -> Unit,
    onShowSetting: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        Text(
            text = Language.TARGET_DEVICE,
            style = MaterialTheme.typography.subtitle2
        )

        DropDownDeviceMenu(
            devices = devices,
            selectedDevice = selectedDevice,
            onSelectDevice = onSelectDevice,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = Language.TOOL,
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
            iconDescription = "Setting",
            text = "Setting",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .clickableNoRipple { onShowSetting() }
                .padding(vertical = 8.dp)
        )
    }
}

@Preview
@Composable
private fun MenuScreen_Preview() {
    MenuScreen(
        devices = emptyList(),
        selectedDevice = null,
        onSelectDevice = {},
        menus = listOf(Menu.Command, Menu.InputText, Menu.Screenshot),
        selectedMenu = Menu.Command,
        onSelectMenu = {},
        onShowSetting = {}
    )
}
