package jp.kaleidot725.adbpad.ui.screen.screenshot.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.composables.icons.lucide.Camera
import com.composables.icons.lucide.Lucide
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.ui.common.resource.UserColor
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground

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

    LaunchedEffect(canCapture) {
        if (!canCapture) {
            expanded = false
        }
    }

    LaunchedEffect(isCapturing) {
        if (isCapturing) {
            expanded = false
        }
    }

    val hasCommands = commands.isNotEmpty()
    val buttonEnabled = canCapture && hasCommands
    val canOpenMenu = buttonEnabled && !isCapturing

    Box(modifier = modifier.wrapContentSize()) {
        Box(
            modifier =
                Modifier
                    .padding(vertical = 4.dp, horizontal = 4.dp)
                    .size(32.dp)
                    .alpha(if (buttonEnabled) 1f else 0.38f)
                    .clip(RoundedCornerShape(4.dp))
                    .clickableBackground(isDarker = MaterialTheme.colorScheme.surface.luminance() <= 0.5)
                    .clickable(enabled = canOpenMenu) { expanded = true },
            contentAlignment = Alignment.Center,
        ) {
            if (isCapturing) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            } else {
                Icon(
                    imageVector = Lucide.Camera,
                    contentDescription = "capture screenshot",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(20.dp),
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier =
                Modifier
                    .align(Alignment.TopEnd)
                    .width(250.dp)
                    .background(
                        color = UserColor.getDropdownBackgroundColor(),
                        shape = MaterialTheme.shapes.extraSmall,
                    ).border(
                        width = 1.dp,
                        color = UserColor.getSplitterColor(),
                        shape = MaterialTheme.shapes.extraSmall,
                    ),
            offset = DpOffset(x = (-12).dp, y = (-4).dp),
            properties = PopupProperties(focusable = true),
        ) {
            commands.forEach { command ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = command.title,
                            style = MaterialTheme.typography.titleSmall,
                        )
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
