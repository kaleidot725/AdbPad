package jp.kaleidot725.adbpad.component.layout

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalBorder() {
    Divider(color = Color.LightGray, modifier = Modifier.height(2.dp).fillMaxWidth())
}
