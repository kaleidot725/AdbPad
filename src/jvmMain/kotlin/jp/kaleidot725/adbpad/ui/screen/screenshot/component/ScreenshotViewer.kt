package jp.kaleidot725.adbpad.ui.screen.screenshot.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.screenshot.Screenshot
import jp.kaleidot725.adbpad.ui.common.resource.defaultBorder

@Composable
fun ScreenshotViewer(
    screenshot: Screenshot,
    isCapturing: Boolean,
    onOpenDirectory: () -> Unit,
    onCopyScreenshot: () -> Unit,
    onDeleteScreenshot: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        ScreenshotActions(
            enabled = screenshot.file != null,
            onOpen = onOpenDirectory,
            onCopy = onCopyScreenshot,
            onDelete = onDeleteScreenshot,
            modifier = Modifier.height(48.dp).padding(horizontal = 12.dp).align(Alignment.End),
        )

        Divider(modifier = Modifier.height(1.dp).fillMaxWidth().defaultBorder())

        if (isCapturing) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            if (screenshot.file != null) {
                AsyncImage(
                    model = screenshot.file,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                )
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = Language.notFoundScreenshot,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ScreenshotViewer_Preview() {
    ScreenshotViewer(
        screenshot = Screenshot(null),
        isCapturing = false,
        onOpenDirectory = {},
        onCopyScreenshot = {},
        onDeleteScreenshot = {},
        modifier = Modifier.fillMaxSize(),
    )
}
