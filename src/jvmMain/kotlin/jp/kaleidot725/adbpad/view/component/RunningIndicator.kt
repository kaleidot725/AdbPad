package jp.kaleidot725.adbpad.view.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RunningIndicator(color: Color = MaterialTheme.colors.onPrimary) {
    CircularProgressIndicator(
        color = color,
        strokeWidth = 2.dp,
        modifier = Modifier.size(20.dp),
    )
}

@Preview
@Composable
private fun RunningIndicator_Preview() {
    RunningIndicator()
}
