package jp.kaleidot725.adbpad.ui.screen.screenshot.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand

@Composable
fun ScreenshotMenu(
    commands: List<ScreenshotCommand>,
    canCapture: Boolean,
    isCapturing: Boolean,
    onTakeScreenshot: (ScreenshotCommand) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        ScreenshotDropDownButton(
            commands = commands,
            canCapture = canCapture,
            isCapturing = isCapturing,
            onTakeScreenshot = onTakeScreenshot,
        )
    }
}

@Preview
@Composable
private fun Preview() {
    ScreenshotMenu(
        commands = listOf(ScreenshotCommand.Current(isRunning = false)),
        canCapture = false,
        isCapturing = false,
        onTakeScreenshot = {}
    )
}