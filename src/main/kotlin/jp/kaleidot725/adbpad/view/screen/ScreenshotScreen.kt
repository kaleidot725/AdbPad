package jp.kaleidot725.adbpad.view.page

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.view.component.viewer.ImageView

@Composable
fun ScreenshotScreen(
    image1: ImageBitmap?,
    image2: ImageBitmap?,
    onTakeScreenShot: () -> Unit,
    onTakeThemeScreenshot: () -> Unit
) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        ImageView(
            image1 = image1,
            image2 = image2,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .border(BorderStroke(1.dp, Color.LightGray))
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.wrapContentSize().align(Alignment.End)
        ) {
            Button(onClick = { onTakeThemeScreenshot() }) {
                Text(
                    text = "テーマ毎に撮影する",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(150.dp)
                )
            }

            Button(onClick = { onTakeScreenShot() }) {
                Text(
                    text = "撮影する",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(150.dp)
                )
            }
        }
    }
}
