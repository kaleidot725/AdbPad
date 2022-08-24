package jp.kaleidot725.adbpad.view.page

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.view.component.screenshot.ScreenshotMenu
import jp.kaleidot725.adbpad.view.component.screenshot.ScreenshotViewer

@Composable
fun ScreenshotScreen(
    image1: ImageBitmap?,
    image2: ImageBitmap?,
    onTakeScreenShot: () -> Unit,
    onTakeThemeScreenshot: () -> Unit
) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        ScreenshotViewer(
            image1 = image1,
            image2 = image2,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .border(BorderStroke(1.dp, Color.LightGray))
        )

        ScreenshotMenu(
            onTakeScreenShot = onTakeScreenShot,
            onTakeThemeScreenshot = onTakeThemeScreenshot,
            modifier = Modifier.wrapContentSize().align(Alignment.End)
        )
    }
}

@Composable
@Preview
private fun ScreenshotScreen_Preview() {
    ScreenshotScreen(
        image1 = null,
        image2 = null,
        onTakeScreenShot = {},
        onTakeThemeScreenshot = {}
    )
}
