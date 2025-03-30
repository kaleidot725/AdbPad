package jp.kaleidot725.adbpad.ui.screen.device

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.component.button.FloatingDialog
import jp.kaleidot725.adbpad.ui.component.text.Title
import jp.kaleidot725.adbpad.ui.screen.device.component.DeviceHeader
import jp.kaleidot725.adbpad.ui.screen.device.component.DeviceItem

@Composable
fun DeviceScreen(
    devices: List<Device>,
    onUpdateDeviceName: (Device, String) -> Unit,
    onClose: () -> Unit,
) {
    FloatingDialog(
        modifier =
            Modifier
                .width(960.dp)
                .fillMaxHeight()
                .padding(vertical = 32.dp),
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Title(
                    text = Language.targetDevice,
                    modifier = Modifier.fillMaxWidth(),
                )

                Divider()

                Column(
                    modifier = Modifier.weight(1.0f),
                ) {
                    DeviceHeader(modifier = Modifier.fillMaxWidth())

                    devices.forEach { device ->
                        DeviceItem(
                            device = device,
                            onUpdateName = { onUpdateDeviceName(device, it) },
                            modifier = Modifier.fillMaxWidth(),
                        )

                        Divider()
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.wrapContentSize().align(Alignment.End),
                ) {
                    Button(
                        onClick = onClose,
                    ) {
                        Text(
                            text = Language.cancel,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.width(100.dp),
                        )
                    }

                    Button(
                        onClick = onClose,
                    ) {
                        Text(
                            text = Language.save,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.width(100.dp),
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DeviceScreen(
        devices = emptyList(),
        onClose = {},
        onUpdateDeviceName = { _, _ -> },
    )
}
