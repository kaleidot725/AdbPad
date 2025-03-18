package jp.kaleidot725.adbpad.ui.screen.menu.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.device.DeviceState
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground

@Composable
fun DropDownDeviceMenu(
    devices: List<Device>,
    selectedDevice: Device?,
    onSelectDevice: (Device) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    var dropDownWidth by remember { mutableStateOf(0) }

    Box(modifier) {
        DeviceSelector(
            selectedDevice = selectedDevice,
            modifier =
                Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(4.dp))
                    .clickableBackground(isDarker = !MaterialTheme.colors.isLight)
                    .clickable { if (!expanded && devices.isNotEmpty()) expanded = true }
                    .onSizeChanged { dropDownWidth = it.width },
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { dropDownWidth.toDp() }),
        ) {
            devices.forEach { device ->
                DropdownMenuItem(
                    onClick = {
                        onSelectDevice(device)
                        expanded = false
                    },
                ) {
                    Text(
                        text = device.serial,
                        color = MaterialTheme.colors.onBackground,
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
        onSelectDevice = {},
    )
}
