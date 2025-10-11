package jp.kaleidot725.adbpad.ui.screen.device.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.device.DeviceSettings
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.component.text.DefaultOutlineTextField
import jp.kaleidot725.adbpad.ui.component.text.SubTitle
import jp.kaleidot725.scrcpykt.option.AudioCodec
import jp.kaleidot725.scrcpykt.option.AudioSource
import jp.kaleidot725.scrcpykt.option.LogLevel
import jp.kaleidot725.scrcpykt.option.VideoCodec
import jp.kaleidot725.scrcpykt.option.VideoSource

@Composable
fun DeviceScrcpyPane(
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
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        val scrcpyOptions = deviceSettings.scrcpyOptions
        val onUpdateOptions = { newOptions: jp.kaleidot725.adbpad.domain.model.device.ScrcpyOptions ->
            onUpdateDeviceSettings(deviceSettings.copy(scrcpyOptions = newOptions))
        }

        // Video Options
        SubTitle(
            text = Language.videoOptionsSection,
            modifier = Modifier.padding(horizontal = 4.dp),
        )

        // No Video checkbox first
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Checkbox(
                checked = scrcpyOptions.noVideo,
                onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(noVideo = it)) },
            )
            Text(
                Language.noVideoLabel,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp),
            )
        }

        // Max Size
        DefaultOutlineTextField(
            id = "maxSize",
            initialText = scrcpyOptions.maxSize?.toString() ?: "",
            onUpdateText = { text ->
                onUpdateOptions(scrcpyOptions.copy(maxSize = text.toIntOrNull()))
            },
            label = Language.maxSizeLabel,
            placeHolder = "1920",
            isError = false,
            enabled = !scrcpyOptions.noVideo,
            modifier = Modifier.fillMaxWidth(),
        )

        // Video Bit Rate
        DefaultOutlineTextField(
            id = "videoBitRate",
            initialText = scrcpyOptions.videoBitRate?.toString() ?: "",
            onUpdateText = { text ->
                onUpdateOptions(scrcpyOptions.copy(videoBitRate = text.toIntOrNull()))
            },
            label = Language.videoBitRateLabel,
            placeHolder = "8000000",
            isError = false,
            enabled = !scrcpyOptions.noVideo,
            modifier = Modifier.fillMaxWidth(),
        )

        // Max FPS
        DefaultOutlineTextField(
            id = "maxFps",
            initialText = scrcpyOptions.maxFps?.toString() ?: "",
            onUpdateText = { text ->
                onUpdateOptions(scrcpyOptions.copy(maxFps = text.toIntOrNull()))
            },
            label = Language.maxFpsLabel,
            placeHolder = "60",
            isError = false,
            enabled = !scrcpyOptions.noVideo,
            modifier = Modifier.fillMaxWidth(),
        )

        // Video Codec Dropdown
        EnumDropDown(
            selectedValue = scrcpyOptions.videoCodec,
            values = VideoCodec.entries.toList(),
            onValueSelected = { codec ->
                onUpdateOptions(scrcpyOptions.copy(videoCodec = codec))
            },
            displayName = { it?.value ?: Language.autoLabel },
            label = Language.videoCodecLabel,
            enabled = !scrcpyOptions.noVideo,
            modifier = Modifier.fillMaxWidth(),
        )

        // Video Source Dropdown
        EnumDropDown(
            selectedValue = scrcpyOptions.videoSource,
            values = VideoSource.entries.toList(),
            onValueSelected = { source ->
                onUpdateOptions(scrcpyOptions.copy(videoSource = source))
            },
            displayName = { it?.value ?: Language.autoLabel },
            label = Language.videoSourceLabel,
            enabled = !scrcpyOptions.noVideo,
            modifier = Modifier.fillMaxWidth(),
        )

        // Audio Options
        SubTitle(
            text = Language.audioOptionsSection,
            modifier = Modifier.padding(horizontal = 4.dp),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Checkbox(
                checked = scrcpyOptions.noAudio,
                onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(noAudio = it)) },
            )
                Text(
                    Language.noAudioLabel,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp),
                )
        }

        // Audio Bit Rate
        DefaultOutlineTextField(
            id = "audioBitRate",
            initialText = scrcpyOptions.audioBitRate?.toString() ?: "",
            onUpdateText = { text ->
                onUpdateOptions(scrcpyOptions.copy(audioBitRate = text.toIntOrNull()))
            },
            label = Language.audioBitRateLabel,
            placeHolder = "128000",
            isError = false,
            enabled = !scrcpyOptions.noAudio,
            modifier = Modifier.fillMaxWidth(),
        )

        // Audio Buffer
        DefaultOutlineTextField(
            id = "audioBuffer",
            initialText = scrcpyOptions.audioBuffer?.toString() ?: "",
            onUpdateText = { text ->
                onUpdateOptions(scrcpyOptions.copy(audioBuffer = text.toIntOrNull()))
            },
            label = Language.audioBufferLabel,
            placeHolder = "50",
            isError = false,
            enabled = !scrcpyOptions.noAudio,
            modifier = Modifier.fillMaxWidth(),
        )

        // Audio Codec Dropdown
        EnumDropDown(
            selectedValue = scrcpyOptions.audioCodec,
            values = AudioCodec.entries.toList(),
            onValueSelected = { codec ->
                onUpdateOptions(scrcpyOptions.copy(audioCodec = codec))
            },
            displayName = { it?.value ?: Language.autoLabel },
            label = Language.audioCodecLabel,
            enabled = !scrcpyOptions.noAudio,
            modifier = Modifier.fillMaxWidth(),
        )

        // Audio Source Dropdown
        EnumDropDown(
            selectedValue = scrcpyOptions.audioSource,
            values = AudioSource.entries.toList(),
            onValueSelected = { source ->
                onUpdateOptions(scrcpyOptions.copy(audioSource = source))
            },
            displayName = { it?.value ?: Language.autoLabel },
            label = Language.audioSourceLabel,
            enabled = !scrcpyOptions.noAudio,
            modifier = Modifier.fillMaxWidth(),
        )

        // Display Options
        SubTitle(
            text = Language.displayOptionsSection,
            modifier = Modifier.padding(horizontal = 4.dp),
        )

        // Window Title
        DefaultOutlineTextField(
            id = "windowTitle",
            initialText = scrcpyOptions.windowTitle ?: "",
            onUpdateText = { text ->
                onUpdateOptions(scrcpyOptions.copy(windowTitle = text.ifBlank { null }))
            },
            label = Language.windowTitleLabel,
            placeHolder = Language.customTitlePlaceholder,
            isError = false,
            modifier = Modifier.fillMaxWidth(),
        )

        // Display ID
        DefaultOutlineTextField(
            id = "displayId",
            initialText = scrcpyOptions.displayId?.toString() ?: "",
            onUpdateText = { text ->
                onUpdateOptions(scrcpyOptions.copy(displayId = text.toIntOrNull()))
            },
            label = Language.displayIdLabel,
            placeHolder = "0",
            isError = false,
            modifier = Modifier.fillMaxWidth(),
        )

        // Window X
        DefaultOutlineTextField(
            id = "windowX",
            initialText = scrcpyOptions.windowX?.toString() ?: "",
            onUpdateText = { text ->
                onUpdateOptions(scrcpyOptions.copy(windowX = text.toIntOrNull()))
            },
            label = Language.windowXLabel,
            placeHolder = "100",
            isError = false,
            modifier = Modifier.fillMaxWidth(),
        )

        // Window Y
        DefaultOutlineTextField(
            id = "windowY",
            initialText = scrcpyOptions.windowY?.toString() ?: "",
            onUpdateText = { text ->
                onUpdateOptions(scrcpyOptions.copy(windowY = text.toIntOrNull()))
            },
            label = Language.windowYLabel,
            placeHolder = "100",
            isError = false,
            modifier = Modifier.fillMaxWidth(),
        )

        // Window Width
        DefaultOutlineTextField(
            id = "windowWidth",
            initialText = scrcpyOptions.windowWidth?.toString() ?: "",
            onUpdateText = { text ->
                onUpdateOptions(scrcpyOptions.copy(windowWidth = text.toIntOrNull()))
            },
            label = Language.windowWidthLabel,
            placeHolder = "800",
            isError = false,
            modifier = Modifier.fillMaxWidth(),
        )

        // Window Height
        DefaultOutlineTextField(
            id = "windowHeight",
            initialText = scrcpyOptions.windowHeight?.toString() ?: "",
            onUpdateText = { text ->
                onUpdateOptions(scrcpyOptions.copy(windowHeight = text.toIntOrNull()))
            },
            label = Language.windowHeightLabel,
            placeHolder = "600",
            isError = false,
            modifier = Modifier.fillMaxWidth(),
        )

        // Boolean Options
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Checkbox(
                    checked = scrcpyOptions.fullscreen,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(fullscreen = it)) },
                )
                Text(
                    Language.fullscreenLabel,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Checkbox(
                    checked = scrcpyOptions.alwaysOnTop,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(alwaysOnTop = it)) },
                )
                Text(
                    Language.alwaysOnTopLabel,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
        }

        // Control Options
        SubTitle(
            text = Language.controlOptionsSection,
            modifier = Modifier.padding(horizontal = 4.dp),
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Checkbox(
                    checked = scrcpyOptions.stayAwake,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(stayAwake = it)) },
                )
                Text(
                    Language.stayAwakeLabel,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Checkbox(
                    checked = scrcpyOptions.turnScreenOff,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(turnScreenOff = it)) },
                )
                Text(
                    Language.turnScreenOffLabel,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Checkbox(
                    checked = scrcpyOptions.powerOffOnClose,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(powerOffOnClose = it)) },
                )
                Text(
                    Language.powerOffOnCloseLabel,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Checkbox(
                    checked = scrcpyOptions.showTouches,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(showTouches = it)) },
                )
                Text(
                    Language.showTouchesLabel,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Checkbox(
                    checked = scrcpyOptions.disableScreensaver,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(disableScreensaver = it)) },
                )
                Text(
                    Language.disableScreensaverLabel,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
        }

        // Logging Options
        SubTitle(
            text = Language.loggingOptionsSection,
            modifier = Modifier.padding(horizontal = 4.dp),
        )

        // Log Level Dropdown
        EnumDropDown(
            selectedValue = scrcpyOptions.verbosity,
            values = LogLevel.entries.toList(),
            onValueSelected = { level ->
                onUpdateOptions(scrcpyOptions.copy(verbosity = level))
            },
            displayName = { it?.name?.replaceFirstChar { char -> char.uppercaseChar() } ?: Language.defaultLabel },
            label = Language.logLevelLabel,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun <T> EnumDropDown(
    selectedValue: T?,
    values: List<T>,
    onValueSelected: (T?) -> Unit,
    displayName: (T?) -> String,
    label: String,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        OutlinedTextField(
            value = displayName(selectedValue),
            onValueChange = { },
            label = { Text(label, style = MaterialTheme.typography.bodySmall) },
            readOnly = true,
            enabled = enabled,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                )
            },
            textStyle = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth(),
        )

        // Invisible clickable overlay
        Box(
            modifier =
                Modifier
                    .matchParentSize()
                    .clickable(enabled = enabled) {
                        if (enabled) expanded = true
                    },
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            DropdownMenuItem(
                text = { Text(displayName(null), style = MaterialTheme.typography.bodySmall) },
                onClick = {
                    onValueSelected(null)
                    expanded = false
                },
            )

            values.forEach { value ->
                DropdownMenuItem(
                    text = { Text(displayName(value), style = MaterialTheme.typography.bodySmall) },
                    onClick = {
                        onValueSelected(value)
                        expanded = false
                    },
                )
            }
        }
    }
}
