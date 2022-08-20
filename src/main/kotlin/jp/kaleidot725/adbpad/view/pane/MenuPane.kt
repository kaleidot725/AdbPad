package jp.kaleidot725.adbpad.view.component.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.malinskiy.adam.request.device.Device
import jp.kaleidot725.adbpad.model.Menu

@Composable
fun MenuPane(
    devices: List<Device>,
    selectedDevice: Device?,
    onSelectDevice: (Device) -> Unit,
    menus: List<Menu>,
    selectedMenu: Menu,
    onSelectMenu: (Menu) -> Unit,
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
    }
}
