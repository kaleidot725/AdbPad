package jp.kaleidot725.adbpad.view.component.menu

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.malinskiy.adam.request.device.Device
import jp.kaleidot725.adbpad.view.resource.Menu
import jp.kaleidot725.adbpad.view.resource.String

@Composable
fun MenuScreen(
    devices: List<Device>,
    selectedDevice: Device?,
    onSelectDevice: (Device) -> Unit,
    menus: List<Menu>,
    selectedMenu: Menu?,
    onSelectMenu: (Menu) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        Text(
            text = String.TARGET_DEVICE,
            style = MaterialTheme.typography.subtitle2
        )

        DropDownDeviceMenu(
            devices = devices,
            selectedDevice = selectedDevice,
            onSelectDevice = onSelectDevice,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = String.TOOL,
            style = MaterialTheme.typography.subtitle2
        )

        MenuList(
            menus = menus,
            selectedMenu = selectedMenu,
            onSelectMenu = onSelectMenu,
            modifier = Modifier.fillMaxWidth().weight(weight = 0.9f, fill = true)
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
        menus = Menu.values().toList(),
        selectedMenu = Menu.values().first(),
        onSelectMenu = {}
    )
}
