package jp.kaleidot725.adbpad.view.component.screenshot

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyLira
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.screenshot.ScreenshotPreview
import java.awt.datatransfer.Clipboard

@Composable
fun ScreenshotViewer(
    screenshotPreview: ScreenshotPreview,
    isCapturing: Boolean,
    onCopyScreenshot: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        if (isCapturing) {
            Box(Modifier.align(Alignment.Center)) {
                CircularProgressIndicator()
            }
        } else {
            val file = screenshotPreview.file
            val bitmap = rememberUpdatedState(file?.let { loadImageBitmap(it.inputStream()) })
            val bitmapValue = bitmap.value
            if (bitmapValue != null) {
                IconButton(
                    onClick = onCopyScreenshot,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                ) {
                    Icon(imageVector = Icons.Default.FileCopy, contentDescription = "copy")
                }
                Image(
                    bitmap = bitmapValue,
                    contentDescription = "preview image",
                    modifier = Modifier.fillMaxHeight().align(Alignment.Center)
                )
            } else {
                Text(
                    text = Language.NOT_FOUND_SCREEN_SHOT,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview
@Composable
private fun ScreenshotViewer_Preview() {
    ScreenshotViewer(
        screenshotPreview = ScreenshotPreview(null),
        isCapturing = false,
        onCopyScreenshot = {},
        modifier = Modifier.fillMaxSize()
    )
}
