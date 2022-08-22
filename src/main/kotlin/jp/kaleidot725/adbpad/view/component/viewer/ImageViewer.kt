package jp.kaleidot725.adbpad.view.component.viewer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import java.io.File

@Composable
fun ImageView(
    image1: ImageBitmap?,
    image2: ImageBitmap?,
    modifier: Modifier
) {
    Row(modifier) {
        if (image1 != null) {
            Image(
                bitmap = image1,
                contentDescription = "preview image1",
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f)
                    .border(border = BorderStroke(1.dp, Color.Black))
            )
        }

        if (image2 != null) {
            Image(
                bitmap = image2,
                contentDescription = "preview image2",
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f)
                    .border(border = BorderStroke(1.dp, Color.Black))
            )
        }
    }
}