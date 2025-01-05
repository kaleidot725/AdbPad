package jp.kaleidot725.adbpad.ui.screen.screenshot

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.UserColor
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.model.screenshot.Screenshot
import jp.kaleidot725.adbpad.ui.screen.screenshot.component.ScreenshotDropDownButton
import jp.kaleidot725.adbpad.ui.screen.screenshot.component.ScreenshotViewer

@Composable
fun ScreenshotScreen(
    screenshot: Screenshot,
    screenshots: List<Screenshot>,
    canCapture: Boolean,
    isCapturing: Boolean,
    commands: List<ScreenshotCommand>,
    onCopyScreenshot: () -> Unit,
    onDeleteScreenshot: () -> Unit,
    onTakeScreenshot: (ScreenshotCommand) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize().padding(16.dp),
    ) {
        ScreenshotViewer(
            screenshot = screenshot,
            isCapturing = isCapturing,
            onCopyScreenshot = onCopyScreenshot,
            onDeleteScreenshot = onDeleteScreenshot,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
                    .border(
                        border = BorderStroke(1.dp, UserColor.getSplitterColor()),
                        shape = RoundedCornerShape(4.dp),
                    ),
        )

        Text(screenshots.toString())

        ScreenshotDropDownButton(
            commands = commands,
            canCapture = canCapture,
            isCapturing = isCapturing,
            onTakeScreenshot = onTakeScreenshot,
            modifier = Modifier.wrapContentSize().align(Alignment.End),
        )
    }
}

@Composable
@Preview
private fun ScreenshotScreen_Preview() {
    ScreenshotScreen(
        screenshot = Screenshot(null),
        screenshots = emptyList(),
        canCapture = true,
        isCapturing = false,
        commands = emptyList(),
        onCopyScreenshot = {},
        onDeleteScreenshot = {},
        onTakeScreenshot = {},
    )
}
