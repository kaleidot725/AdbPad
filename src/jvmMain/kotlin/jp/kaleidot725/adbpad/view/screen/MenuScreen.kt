package jp.kaleidot725.adbpad.view.common.menu

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
import com.malinskiy.adam.request.device.Device
import jp.kaleidot725.adbpad.extension.clickableNoRipple
import jp.kaleidot725.adbpad.model.data.Menu
import jp.kaleidot725.adbpad.view.resource.StringRes

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
            text = StringRes.TARGET_DEVICE,
            style = MaterialTheme.typography.subtitle2
        )

        DropDownDeviceMenu(
            devices = devices,
            selectedDevice = selectedDevice,
            onSelectDevice = onSelectDevice,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = StringRes.TOOL,
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
