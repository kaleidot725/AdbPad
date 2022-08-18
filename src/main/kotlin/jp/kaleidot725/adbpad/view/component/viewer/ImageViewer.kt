package jp.kaleidot725.adbpad.view.component.viewer

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ImageView(
    image1: String,
    image2: String,
    modifier: Modifier
) {
    Row(modifier) {
        Text(
            text = image1,
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.5f)
                .border(border = BorderStroke(1.dp, Color.Black))
        )
        Text(
            text = image2,
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.5f)
                .border(border = BorderStroke(1.dp, Color.Black))
        )
    }
}

@Preview
@Composable
private fun ImageView_Preview() {
    ImageView("TEST1", "TEST2", Modifier.fillMaxSize())
}
