package jp.kaleidot725.adbpad.ui.screen.device.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.device.DeviceState
import jp.kaleidot725.adbpad.ui.component.text.DefaultTextField

@Composable
fun DeviceItem(
    device: Device,
    onUpdateName: (String) -> Unit,
    idWeight: Float = 0.5f,
    nameWeight: Float = 1.0f,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Text(
            text = device.serial,
            modifier = Modifier.weight(idWeight).alignByBaseline(),
        )

        DefaultTextField(
            id = device.serial,
            initialText = device.name,
            onUpdateText = onUpdateName,
            modifier = Modifier.weight(nameWeight).alignByBaseline(),
            placeHolder = "",
            maxLines = 1,
        )
    }
}

@Preview
@Composable
private fun Preview() {
    DeviceItem(
        device = Device(serial = "serial", name = "name", state = DeviceState.CONNECTING),
        onUpdateName = {},
    )
}
