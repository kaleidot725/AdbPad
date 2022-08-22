package jp.kaleidot725.adbpad.view.component.viewer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap

@Composable
fun ImageView(
    image1: ImageBitmap?,
    image2: ImageBitmap?,
    modifier: Modifier
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
                text = "スクリーンショットがありません",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
