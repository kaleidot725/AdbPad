package jp.kaleidot725.adbpad.ui.screen.device

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.device.DeviceSettings
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.component.button.FloatingDialog
import jp.kaleidot725.adbpad.ui.component.text.DefaultOutlineTextField
import jp.kaleidot725.adbpad.ui.component.text.SubTitle
import jp.kaleidot725.adbpad.ui.component.text.Title
import jp.kaleidot725.adbpad.ui.screen.device.section.ScrcpyOptionsSection

@Composable
fun DeviceSettingsScreen(
    device: Device,
    deviceSettings: DeviceSettings,
    onUpdateDeviceSettings: (DeviceSettings) -> Unit,
    onSave: () -> Unit,
    onCancel: () -> Unit,
    isSaving: Boolean = false,
    modifier: Modifier = Modifier,
) {
    FloatingDialog(
        modifier =
            modifier
                .width(800.dp)
                .fillMaxHeight()
                .padding(vertical = 32.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 80.dp),
            ) {
                Title(
                    text = "${Language.deviceSettingsTitle} - ${device.displayName}",
                    modifier = Modifier.fillMaxWidth(),
                )

                HorizontalDivider(modifier = Modifier.fillMaxWidth())

                // Device Name Section
                SubTitle(
                    text = Language.deviceNameSection,
                    modifier = Modifier.padding(horizontal = 4.dp),
                )

                DefaultOutlineTextField(
                    id = device.serial,
                    initialText = deviceSettings.customName ?: device.name,
                    onUpdateText = { newName ->
                        onUpdateDeviceSettings(
                            deviceSettings.copy(
                                customName = if (newName.isNotBlank() && newName != device.name) newName else null,
                            ),
                        )
                    },
                    label = Language.customDeviceNameLabel,
                    placeHolder = device.name,
                    isError = false,
                    modifier = Modifier.fillMaxWidth(),
                )

                HorizontalDivider(modifier = Modifier.fillMaxWidth())

                // Scrcpy Settings Section
                SubTitle(
                    text = Language.scrcpySettingsSection,
                    modifier = Modifier.padding(horizontal = 4.dp),
                )

                ScrcpyOptionsSection(
                    scrcpyOptions = deviceSettings.scrcpyOptions,
                    onUpdateOptions = { newOptions ->
                        onUpdateDeviceSettings(deviceSettings.copy(scrcpyOptions = newOptions))
                    },
                )
            }

            // Action Buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.align(Alignment.BottomEnd),
            ) {
                Button(
                    onClick = onCancel,
                    enabled = !isSaving,
                ) {
                    Text(
                        text = Language.cancel,
                        modifier = Modifier.width(100.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Button(
                    onClick = onSave,
                    enabled = !isSaving,
                ) {
                    if (isSaving) {
                        Box(modifier = Modifier.width(100.dp)) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(12.dp).align(Alignment.Center),
                            )
                        }
                    } else {
                        Text(
                            text = Language.save,
                            modifier = Modifier.width(100.dp),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}
