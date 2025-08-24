package jp.kaleidot725.adbpad.ui.section.top.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Settings
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.device.DeviceState
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.common.resource.UserColor
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
            modifier =
                Modifier
                    .widthIn(min = with(LocalDensity.current) { dropDownWidth.toDp() })
                    .background(
                        color = UserColor.getDropdownBackgroundColor(),
                        shape = RoundedCornerShape(4.dp),
                    ).border(
                        width = 1.dp,
                        color = UserColor.getSplitterColor(),
                        shape = RoundedCornerShape(4.dp),
                    ),
        ) {
            Row(
                modifier = Modifier.height(22.dp).padding(start = 16.dp, end = 8.dp),
            ) {
                Text(
                    text = Language.targetDevice,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.align(Alignment.CenterVertically).padding(bottom = 2.dp),
                )

                Spacer(modifier = Modifier.weight(1.0f))

                CommandIconButton(
                    image = Lucide.Settings,
                    onClick = {
                        expanded = false
                        onOpenDevice()
                    },
                    modifier = Modifier.align(Alignment.CenterVertically).size(24.dp),
                )
            }

            devices.forEach { device ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = device.displayName,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(bottom = 4.dp),
                        )
                    },
                    onClick = {
                        onSelectDevice(device)
                        expanded = false
                    },
                    modifier = Modifier.height(24.dp),
                )
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
