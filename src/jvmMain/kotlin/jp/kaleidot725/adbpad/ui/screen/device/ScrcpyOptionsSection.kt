package jp.kaleidot725.adbpad.ui.screen.device

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.device.ScrcpyOptions
import jp.kaleidot725.adbpad.ui.component.text.DefaultOutlineTextField
import jp.kaleidot725.scrcpykt.option.AudioCodec
import jp.kaleidot725.scrcpykt.option.AudioSource
import jp.kaleidot725.scrcpykt.option.CameraFacing
import jp.kaleidot725.scrcpykt.option.CaptureOrientation
import jp.kaleidot725.scrcpykt.option.GamepadMode
import jp.kaleidot725.scrcpykt.option.KeyboardMode
import jp.kaleidot725.scrcpykt.option.LogLevel
import jp.kaleidot725.scrcpykt.option.MouseMode
import jp.kaleidot725.scrcpykt.option.RecordFormat
import jp.kaleidot725.scrcpykt.option.VideoCodec
import jp.kaleidot725.scrcpykt.option.VideoSource

@Composable
fun ScrcpyOptionsSection(
    scrcpyOptions: ScrcpyOptions,
    onUpdateOptions: (ScrcpyOptions) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier,
    ) {
        // Video Options
        Text(
            text = "Video Options",
            style = MaterialTheme.typography.titleMedium,
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
            Text("No Video", modifier = Modifier.padding(start = 8.dp))
        }

        // Max Size
        DefaultOutlineTextField(
            id = "maxSize",
            initialText = scrcpyOptions.maxSize?.toString() ?: "",
            onUpdateText = { text ->
                onUpdateOptions(scrcpyOptions.copy(maxSize = text.toIntOrNull()))
            },
            label = "Max Size",
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
            label = "Video Bit Rate",
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
            label = "Max FPS",
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
            displayName = { it?.value ?: "Auto" },
            label = "Video Codec",
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
            displayName = { it?.value ?: "Auto" },
            label = "Video Source",
            enabled = !scrcpyOptions.noVideo,
            modifier = Modifier.fillMaxWidth(),
        )

        HorizontalDivider(modifier = Modifier.fillMaxWidth())

        // Audio Options
        Text(
            text = "Audio Options",
            style = MaterialTheme.typography.titleMedium,
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Checkbox(
                checked = scrcpyOptions.noAudio,
                onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(noAudio = it)) },
            )
            Text("No Audio", modifier = Modifier.padding(start = 8.dp))
        }

        // Audio Bit Rate
        DefaultOutlineTextField(
            id = "audioBitRate",
            initialText = scrcpyOptions.audioBitRate?.toString() ?: "",
            onUpdateText = { text ->
                onUpdateOptions(scrcpyOptions.copy(audioBitRate = text.toIntOrNull()))
            },
            label = "Audio Bit Rate",
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
            label = "Audio Buffer (ms)",
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
            displayName = { it?.value ?: "Auto" },
            label = "Audio Codec",
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
            displayName = { it?.value ?: "Auto" },
            label = "Audio Source",
            enabled = !scrcpyOptions.noAudio,
            modifier = Modifier.fillMaxWidth(),
        )

        HorizontalDivider(modifier = Modifier.fillMaxWidth())

        // Display Options
        Text(
            text = "Display Options",
            style = MaterialTheme.typography.titleMedium,
        )

        // Window Title
        DefaultOutlineTextField(
            id = "windowTitle",
            initialText = scrcpyOptions.windowTitle ?: "",
            onUpdateText = { text ->
                onUpdateOptions(scrcpyOptions.copy(windowTitle = text.ifBlank { null }))
            },
            label = "Window Title",
            placeHolder = "Custom title",
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
            label = "Display ID",
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
            label = "Window X",
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
            label = "Window Y",
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
            label = "Window Width",
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
            label = "Window Height",
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
                Text("Fullscreen", modifier = Modifier.padding(start = 8.dp))
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Checkbox(
                    checked = scrcpyOptions.alwaysOnTop,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(alwaysOnTop = it)) },
                )
                Text("Always on Top", modifier = Modifier.padding(start = 8.dp))
            }
        }

        HorizontalDivider(modifier = Modifier.fillMaxWidth())

        // Control Options
        Text(
            text = "Control Options",
            style = MaterialTheme.typography.titleMedium,
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
                Text("Stay Awake", modifier = Modifier.padding(start = 8.dp))
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Checkbox(
                    checked = scrcpyOptions.turnScreenOff,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(turnScreenOff = it)) },
                )
                Text("Turn Screen Off", modifier = Modifier.padding(start = 8.dp))
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Checkbox(
                    checked = scrcpyOptions.powerOffOnClose,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(powerOffOnClose = it)) },
                )
                Text("Power Off on Close", modifier = Modifier.padding(start = 8.dp))
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Checkbox(
                    checked = scrcpyOptions.showTouches,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(showTouches = it)) },
                )
                Text("Show Touches", modifier = Modifier.padding(start = 8.dp))
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Checkbox(
                    checked = scrcpyOptions.disableScreensaver,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(disableScreensaver = it)) },
                )
                Text("Disable Screensaver", modifier = Modifier.padding(start = 8.dp))
            }
        }

        HorizontalDivider(modifier = Modifier.fillMaxWidth())

        // Logging Options
        Text(
            text = "Logging Options",
            style = MaterialTheme.typography.titleMedium,
        )

        // Log Level Dropdown
        EnumDropDown(
            selectedValue = scrcpyOptions.verbosity,
            values = LogLevel.entries.toList(),
            onValueSelected = { level ->
                onUpdateOptions(scrcpyOptions.copy(verbosity = level))
            },
            displayName = { it?.name?.replaceFirstChar { char -> char.uppercaseChar() } ?: "Default" },
            label = "Log Level",
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
            label = { Text(label) },
            readOnly = true,
            enabled = enabled,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                )
            },
            modifier = Modifier.fillMaxWidth(),
        )
        
        // Invisible clickable overlay
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable(enabled = enabled) {
                    if (enabled) expanded = true
                }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            // "None" option
            DropdownMenuItem(
                text = { Text(displayName(null)) },
                onClick = {
                    onValueSelected(null)
                    expanded = false
                },
            )

            // Enum values
            values.forEach { value ->
                DropdownMenuItem(
                    text = { Text(displayName(value)) },
                    onClick = {
                        onValueSelected(value)
                        expanded = false
                    },
                )
            }
        }
    }
}
