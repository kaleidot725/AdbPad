package jp.kaleidot725.adbpad.ui.screen.screenshot.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand

@Composable
fun ScreenshotDropDownButton(
    selectedCommand: ScreenshotCommand,
    onSelectCommand: (ScreenshotCommand) -> Unit,
    commands: List<ScreenshotCommand>,
    canCapture: Boolean,
    isCapturing: Boolean,
    onTakeScreenshot: (ScreenshotCommand) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier) {
        ScreenshotButton(
            selectedCommand = selectedCommand,
            canCapture = canCapture,
            isCapturing = isCapturing,
            onTake = { onTakeScreenshot(selectedCommand) },
            onChangeType = { expanded = true },
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(250.dp),
        ) {
            commands.forEach { command ->
                DropdownMenuItem(
                    onClick = {
                        onSelectCommand(command)
                        expanded = false
                    },
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Box(modifier = Modifier.size(20.dp).align(Alignment.CenterVertically)) {
                            if (command == selectedCommand) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxSize(),
                                )
                            }
                        }

                        Text(
                            text = command.title,
                            style = MaterialTheme.typography.subtitle2,
                            modifier = Modifier.align(Alignment.CenterVertically),
                        )
                    }
                }
            }
        }
    }
}
