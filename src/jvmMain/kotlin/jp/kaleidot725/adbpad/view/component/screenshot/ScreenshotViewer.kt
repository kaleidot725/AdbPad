package jp.kaleidot725.adbpad.view.component.screenshot

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.loadImageBitmap
import jp.kaleidot725.adbpad.view.resource.StringRes
import java.io.File

@Composable
fun ScreenshotViewer(
    image1: File?,
    image2: File?,
    modifier: Modifier = Modifier
) {
    val bitmap1 = rememberUpdatedState(image1?.let { loadImageBitmap(it.inputStream()) })
    val bitmap2 = rememberUpdatedState(image2?.let { loadImageBitmap(it.inputStream()) })

    Box(modifier) {
        val bitmapValue1 = bitmap1.value
        val bitmapValue2 = bitmap2.value
        if (bitmapValue1 != null || bitmapValue2 != null) {
            Row {
                if (bitmapValue1 != null) {
                    Image(
                        bitmap = bitmapValue1,
                        contentDescription = "preview image1",
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.5f)
                    )
                }

                if (bitmapValue2 != null) {
                    Image(
                        bitmap = bitmapValue2,
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
