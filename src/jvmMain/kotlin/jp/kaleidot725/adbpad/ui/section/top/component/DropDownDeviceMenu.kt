package jp.kaleidot725.adbpad.ui.section.top.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.device.DeviceState
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.component.button.CommandIconButton

@Composable
fun DropDownDeviceMenu(
    devices: List<Device>,
    selectedDevice: Device?,
    onSelectDevice: (Device) -> Unit,
    onOpenDevice: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    var dropDownWidth by remember { mutableStateOf(0) }

    Box(modifier) {
        DeviceSelector(
            selectedDevice = selectedDevice,
            onClick = { if (!expanded && devices.isNotEmpty()) expanded = true },
            modifier =
                Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(4.dp))
                    .onSizeChanged { dropDownWidth = it.width },
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.widthIn(min = with(LocalDensity.current) { dropDownWidth.toDp() }),
        ) {
            Row(
                modifier = Modifier.height(22.dp).padding(start = 16.dp, end = 8.dp),
            ) {
                Text(
                    text = Language.targetDevice,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.align(Alignment.CenterVertically).padding(bottom = 2.dp),
                )

                Spacer(modifier = Modifier.weight(1.0f))

                CommandIconButton(
                    image = Icons.Default.Settings,
                    onClick = {
                        expanded = false
                        onOpenDevice()
                    },
                    modifier = Modifier.align(Alignment.CenterVertically).size(24.dp),
                )
            }

            devices.forEach { device ->
                DropdownMenuItem(
                    onClick = {
                        onSelectDevice(device)
                        expanded = false
                    },
                    modifier = Modifier.height(24.dp),
                ) {
                    Text(
                        text = device.displayName,
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.align(Alignment.CenterVertically).padding(bottom = 4.dp),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun DeviceList_Preview() {
    val sample = Device("DEVICE", "NAME", DeviceState.DEVICE)
    DropDownDeviceMenu(
        devices = listOf(sample),
        selectedDevice = sample,
        onSelectDevice = {},
        onOpenDevice = {},
    )
}
