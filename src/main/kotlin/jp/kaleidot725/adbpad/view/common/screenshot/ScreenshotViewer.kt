package jp.kaleidot725.adbpad.view.common.screenshot

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import jp.kaleidot725.adbpad.view.resource.StringRes

@Composable
fun ScreenshotViewer(
    image1: ImageBitmap?,
    image2: ImageBitmap?,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        if (image1 != null || image2 != null) {
            Row {
                if (image1 != null) {
                    Image(
                        bitmap = image1,
                        contentDescription = "preview image1",
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.5f)
                    )
                }

                if (image2 != null) {
                    Image(
                        bitmap = image2,
                        contentDescription = "preview image2",
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.5f)
                    )
                }
            }
        } else {
            Text(
                text = StringRes.NOT_FOUND_SCREEN_SHOT,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
private fun ScreenshotViewer_Preview() {
    ScreenshotViewer(
        image1 = null,
        image2 = null,
        modifier = Modifier.fillMaxSize()
    )
}
