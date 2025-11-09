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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.device.DeviceSettings
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.common.resource.UserColor
import jp.kaleidot725.adbpad.ui.component.button.FloatingDialog
import jp.kaleidot725.adbpad.ui.screen.device.model.DeviceSettingCategory
import jp.kaleidot725.adbpad.ui.screen.device.section.DeviceCategorySidebar
import jp.kaleidot725.adbpad.ui.screen.device.section.DeviceGeneralPane
import jp.kaleidot725.adbpad.ui.screen.device.section.DeviceScrcpyPane

@Composable
fun DeviceSettingsScreen(
    device: Device,
    deviceSettings: DeviceSettings,
    selectedCategory: DeviceSettingCategory,
    onCategorySelected: (DeviceSettingCategory) -> Unit,
    onUpdateDeviceSettings: (DeviceSettings) -> Unit,
    onSave: () -> Unit,
    onCancel: () -> Unit,
    isSaving: Boolean = false,
    modifier: Modifier = Modifier,
) {
    FloatingDialog(
        modifier =
            modifier
                .width(1000.dp)
                .fillMaxHeight()
                .padding(vertical = 32.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            Text(
                text = "${Language.deviceSettingsTitle} - ${device.displayName}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp),
            )

            Row(
                modifier = Modifier.weight(1f, true).fillMaxWidth(),
            ) {
                DeviceCategorySidebar(
                    categories = DeviceSettingCategory.entries,
                    selectedCategory = selectedCategory,
                    onCategorySelected = onCategorySelected,
                )

                VerticalDivider(
                    thickness = 1.dp,
                    color = UserColor.getSplitterColor(),
                )

                when (selectedCategory) {
                    DeviceSettingCategory.DEVICE -> {
                        DeviceGeneralPane(
                            device = device,
                            deviceSettings = deviceSettings,
                            onUpdateDeviceSettings = onUpdateDeviceSettings,
                            modifier = Modifier.weight(1f),
                        )
                    }
                    DeviceSettingCategory.SCRCPY -> {
                        DeviceScrcpyPane(
                            deviceSettings = deviceSettings,
                            onUpdateDeviceSettings = onUpdateDeviceSettings,
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.End),
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            ) {
                Button(
                    onClick = onCancel,
                    enabled = !isSaving,
                ) {
                    Text(
                        text = Language.cancel,
                        style = MaterialTheme.typography.bodySmall,
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
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.width(100.dp),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}
