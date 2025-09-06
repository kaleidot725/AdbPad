package jp.kaleidot725.adbpad.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.ui.common.extension.clickableNoRipple

@Composable
fun FloatingDialog(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray.copy(alpha = 0.8f)).clickableNoRipple { }) {
        Box(modifier = Modifier.align(Alignment.Center)) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = modifier,
            ) {
                content()
            }
        }
    }
}
