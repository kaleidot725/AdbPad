package jp.kaleidot725.adbpad.ui.component.text

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SubTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(text = text, style = MaterialTheme.typography.titleLarge, modifier = modifier)
}

@Preview
@Composable
private fun Preview() {
    SubTitle("ADB ＆ Android SDK")
}
