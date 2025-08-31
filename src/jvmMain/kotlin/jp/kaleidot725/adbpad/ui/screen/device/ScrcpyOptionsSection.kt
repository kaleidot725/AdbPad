package jp.kaleidot725.adbpad.ui.screen.device

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.device.ScrcpyOptions
import jp.kaleidot725.adbpad.ui.component.text.DefaultOutlineTextField

@Composable
fun ScrcpyOptionsSection(
    scrcpyOptions: ScrcpyOptions,
    onUpdateOptions: (ScrcpyOptions) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        // Display Options
        Text(
            text = "Display Options",
            style = MaterialTheme.typography.titleMedium,
        )
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultOutlineTextField(
                id = "maxSize",
                initialText = scrcpyOptions.maxSize?.toString() ?: "",
                onUpdateText = { text ->
                    onUpdateOptions(scrcpyOptions.copy(maxSize = text.toIntOrNull()))
                },
                label = "Max Size",
                placeHolder = "1920",
                isError = false,
                modifier = Modifier.weight(1f),
            )
            
            DefaultOutlineTextField(
                id = "bitRate",
                initialText = scrcpyOptions.bitRate?.toString() ?: "",
                onUpdateText = { text ->
                    onUpdateOptions(scrcpyOptions.copy(bitRate = text.toIntOrNull()))
                },
                label = "Bit Rate",
                placeHolder = "8000000",
                isError = false,
                modifier = Modifier.weight(1f),
            )
        }
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultOutlineTextField(
                id = "maxFps",
                initialText = scrcpyOptions.maxFps?.toString() ?: "",
                onUpdateText = { text ->
                    onUpdateOptions(scrcpyOptions.copy(maxFps = text.toIntOrNull()))
                },
                label = "Max FPS",
                placeHolder = "60",
                isError = false,
                modifier = Modifier.weight(1f),
            )
            
            DefaultOutlineTextField(
                id = "rotation",
                initialText = scrcpyOptions.rotation?.toString() ?: "",
                onUpdateText = { text ->
                    onUpdateOptions(scrcpyOptions.copy(rotation = text.toIntOrNull()))
                },
                label = "Rotation",
                placeHolder = "0",
                isError = false,
                modifier = Modifier.weight(1f),
            )
        }

        // Boolean Options
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = scrcpyOptions.stayAwake,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(stayAwake = it)) }
                )
                Text("Stay Awake", modifier = Modifier.padding(start = 8.dp))
            }
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = scrcpyOptions.turnScreenOff,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(turnScreenOff = it)) }
                )
                Text("Turn Screen Off", modifier = Modifier.padding(start = 8.dp))
            }
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = scrcpyOptions.powerOffOnClose,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(powerOffOnClose = it)) }
                )
                Text("Power Off on Close", modifier = Modifier.padding(start = 8.dp))
            }
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = scrcpyOptions.showTouches,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(showTouches = it)) }
                )
                Text("Show Touches", modifier = Modifier.padding(start = 8.dp))
            }
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = scrcpyOptions.alwaysOnTop,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(alwaysOnTop = it)) }
                )
                Text("Always on Top", modifier = Modifier.padding(start = 8.dp))
            }
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = scrcpyOptions.fullscreen,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(fullscreen = it)) }
                )
                Text("Fullscreen", modifier = Modifier.padding(start = 8.dp))
            }
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = scrcpyOptions.noControl,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(noControl = it)) }
                )
                Text("No Control (Display Only)", modifier = Modifier.padding(start = 8.dp))
            }
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = scrcpyOptions.noAudio,
                    onCheckedChange = { onUpdateOptions(scrcpyOptions.copy(noAudio = it)) }
                )
                Text("No Audio", modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}