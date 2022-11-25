package jp.kaleidot725.adbpad.view.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.model.screenshot.ScreenshotPreview
import jp.kaleidot725.adbpad.view.component.screenshot.ScreenshotDropDownButton
import jp.kaleidot725.adbpad.view.component.screenshot.ScreenshotViewer

@Composable
fun ScreenshotScreen(
    preview: ScreenshotPreview,
    isCapturing: Boolean,
    commands: List<ScreenshotCommand>,
    onTakeScreenshot: (ScreenshotCommand) -> Unit,
) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        ScreenshotViewer(
            screenshotPreview = preview,
            isCapturing = isCapturing,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .border(
                    border = BorderStroke(1.dp, jp.kaleidot725.adbpad.domain.model.Color.SPLITTER),
                    shape = RoundedCornerShape(4.dp)
                )
        )

        ScreenshotDropDownButton(
            commands = commands,
            isCapturing = isCapturing,
            onTakeScreenshot = onTakeScreenshot,
            modifier = Modifier.wrapContentSize().align(Alignment.End)
        )
    }
}

@Composable
@Preview
private fun ScreenshotScreen_Preview() {
    ScreenshotScreen(
        preview = ScreenshotPreview(emptyList()),
        isCapturing = false,
        commands = emptyList(),
        onTakeScreenshot = {}
    )
}
