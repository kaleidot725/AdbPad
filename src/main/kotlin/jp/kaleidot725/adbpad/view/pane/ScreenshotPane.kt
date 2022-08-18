package jp.kaleidot725.adbpad.view.page

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.view.component.viewer.ImageView

@Composable
fun ScreenShotPane(
    image1: String,
    image2: String,
    onTakeScreenShot: () -> Unit,
    onTakeThemeScreenshot: () -> Unit
) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        ImageView(
            image1 = image1,
            image2 = image2,
            modifier = Modifier.fillMaxWidth().weight(0.5f)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.wrapContentSize().align(Alignment.End)
        ) {
            Button(onClick = { onTakeScreenShot() }) {
                Text(
                    text = "テーマ毎に撮影する",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(150.dp)
                )
            }

            Button(onClick = { onTakeThemeScreenshot() }) {
                Text(
                    text = "撮影する",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(150.dp)
                )
            }
        }
    }
}
