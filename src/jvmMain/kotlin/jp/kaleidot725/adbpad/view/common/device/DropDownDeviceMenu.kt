package jp.kaleidot725.adbpad.view.common.menu

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.malinskiy.adam.request.device.Device
import com.malinskiy.adam.request.device.DeviceState
import jp.kaleidot725.adbpad.view.common.device.DeviceSelector

@Composable
fun DropDownDeviceMenu(
    devices: List<Device>,
    selectedDevice: Device?,
    onSelectDevice: (Device) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier) {
        DeviceSelector(
            selectedDevice = selectedDevice,
            modifier = Modifier.clickable { if (!expanded && devices.isNotEmpty()) expanded = true }
        )

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier.fillMaxWidth()) {
            devices.forEach { device ->
                DropdownMenuItem(
                    onClick = {
                        onSelectDevice(device)
                        expanded = false
                    }
                ) {
                    Text(text = device.serial, style = MaterialTheme.typography.subtitle2)
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
