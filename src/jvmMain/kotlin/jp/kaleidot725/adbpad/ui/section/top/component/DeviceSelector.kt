package jp.kaleidot725.adbpad.ui.section.top.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.device.DeviceState
import jp.kaleidot725.adbpad.domain.model.language.Language

@Composable
fun DeviceSelector(
    selectedDevice: Device?,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Text(
                text = selectedDevice?.serial ?: Language.notFoundDevice,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.align(Alignment.CenterVertically),
            )

            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Device DropDown Icon",
                tint = MaterialTheme.colors.onBackground,
                modifier = Modifier.align(Alignment.CenterVertically).padding(top = 6.dp),
            )
        }
    }
}

@Preview
@Composable
private fun SelectedDevice_Preview() {
    val sample = Device("DEVICE", DeviceState.DEVICE)
    DeviceSelector(selectedDevice = sample)
}
