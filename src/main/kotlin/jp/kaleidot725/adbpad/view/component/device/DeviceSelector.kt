package jp.kaleidot725.adbpad.view.component.device

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.malinskiy.adam.request.device.Device
import com.malinskiy.adam.request.device.DeviceState
import jp.kaleidot725.adbpad.view.resource.StringRes

@Composable
fun DeviceSelector(
    selectedDevice: Device?,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Box(
            modifier = Modifier
                .border(
                    border = BorderStroke(2.dp, MaterialTheme.colors.primary),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(
                text = selectedDevice?.serial ?: StringRes.NOT_FOUND_DEVICE,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.fillMaxWidth()
            )

            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Device DropDown Icon",
                modifier = Modifier.align(Alignment.CenterEnd)
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