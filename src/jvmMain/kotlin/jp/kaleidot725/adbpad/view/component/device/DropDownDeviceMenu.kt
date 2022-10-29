package jp.kaleidot725.adbpad.view.component.menu

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import com.malinskiy.adam.request.device.Device
import com.malinskiy.adam.request.device.DeviceState
import jp.kaleidot725.adbpad.view.common.extension.clickableNoRipple
import jp.kaleidot725.adbpad.view.component.device.DeviceSelector

@Composable
fun DropDownDeviceMenu(
    devices: List<Device>,
    selectedDevice: Device?,
    onSelectDevice: (Device) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var dropDownWidth by remember { mutableStateOf(0) }

    Box(modifier) {
        DeviceSelector(
            selectedDevice = selectedDevice,
            modifier = Modifier
                .clickableNoRipple { if (!expanded && devices.isNotEmpty()) expanded = true }
                .fillMaxWidth()
                .onSizeChanged { dropDownWidth = it.width }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { dropDownWidth.toDp() })
        ) {
            devices.forEach { device ->
                DropdownMenuItem(
                    onClick = {
                        onSelectDevice(device)
                        expanded = false
                    }
                ) {
                    Text(
                        text = device.serial,
                        style = MaterialTheme.typography.subtitle2,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun DeviceList_Preview() {
    val sample = Device("DEVICE", DeviceState.DEVICE)
    DropDownDeviceMenu(
        devices = listOf(sample),
        selectedDevice = sample,
        onSelectDevice = {}
    )
}
