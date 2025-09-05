package jp.kaleidot725.adbpad.ui.screen.device.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.device.DeviceSettings
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.component.text.DefaultOutlineTextField
import jp.kaleidot725.adbpad.ui.component.text.SubTitle

@Composable
fun DeviceGeneralPane(
    device: Device,
    deviceSettings: DeviceSettings,
    onUpdateDeviceSettings: (DeviceSettings) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
                .padding(bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        SubTitle(
            text = Language.customDeviceNameLabel,
            modifier = Modifier.fillMaxWidth(),
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
    }
}
