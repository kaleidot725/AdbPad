package jp.kaleidot725.adbpad.ui.screen.screenshot.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.composables.icons.lucide.Camera
import com.composables.icons.lucide.Lucide
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand

@Composable
fun ScreenshotMenu(
    selectedCommand: ScreenshotCommand,
    onSelectCommand: (ScreenshotCommand) -> Unit,
    commands: List<ScreenshotCommand>,
    canCapture: Boolean,
    isCapturing: Boolean,
    onTakeScreenshot: (ScreenshotCommand) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    LaunchedEffect(canCapture) {
        if (!canCapture) {
            expanded = false
        }
    }

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        if (canCapture) {
            val fabEnabled = !isCapturing

            FloatingActionButton(
                onClick = { if (fabEnabled) expanded = true },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier =
                    Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 12.dp, bottom = 12.dp),
            ) {
                if (isCapturing) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                } else {
                    Icon(
                        imageVector = Lucide.Camera,
                        contentDescription = "capture screenshot",
                    )
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.align(Alignment.TopEnd),
                offset = DpOffset(x = (-12).dp, y = (-4).dp),
                properties = PopupProperties(focusable = true),
            ) {
                commands.forEach { command ->
                    DropdownMenuItem(
                        text = {
                            Text(text = command.title)
                        },
                        onClick = {
                            onSelectCommand(command)
                            onTakeScreenshot(command)
                            expanded = false
                        },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ScreenshotMenu(
        selectedCommand = ScreenshotCommand.Both,
        onSelectCommand = {},
        commands = listOf(ScreenshotCommand.Current),
        canCapture = false,
        isCapturing = false,
        onTakeScreenshot = {},
    )
}
