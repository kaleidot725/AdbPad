package jp.kaleidot725.adbpad.component.layout

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SplitBorder() {
    Divider(color = Color.LightGray, modifier = Modifier.width(2.dp).fillMaxHeight())
}
