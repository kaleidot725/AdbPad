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
import androidx.compose.material3.HorizontalDivider
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
        onDismiss = onCancel,
        modifier =
            modifier
                .width(1000.dp)
                .fillMaxHeight()
                .padding(vertical = 32.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = "${Language.deviceSettingsTitle} - ${device.displayName}",
                style = MaterialTheme.typography.titleMedium,
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 12.dp, bottom = 16.dp),
            )

            HorizontalDivider(color = UserColor.getSplitterColor())

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
                    modifier = Modifier.fillMaxHeight(),
                )

                Box(
                    modifier = Modifier.weight(1f).fillMaxHeight(),
                ) {
                    when (selectedCategory) {
                        DeviceSettingCategory.DEVICE -> {
                            DeviceGeneralPane(
                                device = device,
                                deviceSettings = deviceSettings,
                                onUpdateDeviceSettings = onUpdateDeviceSettings,
                                modifier = Modifier.fillMaxSize(),
                            )
                        }
                        DeviceSettingCategory.SCRCPY -> {
                            DeviceScrcpyPane(
                                deviceSettings = deviceSettings,
                                onUpdateDeviceSettings = onUpdateDeviceSettings,
                                modifier = Modifier.fillMaxSize(),
                            )
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.End),
                        modifier =
                            Modifier
                                .align(Alignment.BottomEnd)
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 12.dp),
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
    }
}
