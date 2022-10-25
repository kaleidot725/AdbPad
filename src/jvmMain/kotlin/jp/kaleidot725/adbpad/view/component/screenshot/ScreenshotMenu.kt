package jp.kaleidot725.adbpad.view.component.screenshot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.view.resource.StringRes

@Composable
fun ScreenshotMenu(
    onTakeScreenShot: () -> Unit,
    onTakeThemeScreenshot: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Button(onClick = { onTakeThemeScreenshot() }) {
            Text(
                text = StringRes.TAKE_THEME_SCREENSHOT,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(150.dp)
            )
        }

        Button(onClick = { onTakeScreenShot() }) {
            Text(
                text = StringRes.TAKE_SCREENSHOT,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(150.dp)
            )
        }
    }
}
