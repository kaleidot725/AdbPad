package jp.kaleidot725.adbpad.ui.screen.menu.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
import jp.kaleidot725.adbpad.ui.common.resource.defaultBorder

@Composable
fun DeviceSelector(
    selectedDevice: Device?,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        Box(
            modifier =
                Modifier
                    .defaultBorder()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
        ) {
            Text(
                text = selectedDevice?.serial ?: Language.notFoundDevice,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.fillMaxWidth(),
            )

            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Device DropDown Icon",
                modifier = Modifier.align(Alignment.CenterEnd),
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