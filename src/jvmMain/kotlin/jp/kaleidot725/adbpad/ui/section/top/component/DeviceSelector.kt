package jp.kaleidot725.adbpad.ui.section.top.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
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
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground

@Composable
fun DeviceSelector(
    selectedDevice: Device?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier =
                Modifier
                    .widthIn(150.dp)
                    .clickableBackground(isDarker = !MaterialTheme.colors.isLight)
                    .clickable(onClick = onClick)
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 4.dp),
        ) {
            Text(
                text = selectedDevice?.displayName ?: Language.notFoundDevice,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.align(Alignment.CenterVertically),
            )

            Spacer(modifier = Modifier.width(4.dp))

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
    val sample = Device("DEVICE", "NAME", DeviceState.DEVICE)
    DeviceSelector(
        selectedDevice = sample,
        onClick = {},
    )
}
