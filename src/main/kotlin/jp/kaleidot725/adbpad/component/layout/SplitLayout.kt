package jp.kaleidot725.adbpad.component

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.kaleidot725.adbpad.component.layout.VerticalBorder

@Composable
fun SplitLayout(
    leftContent: @Composable () -> Unit,
    rightContent: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        leftContent()
        VerticalBorder()
        rightContent()
    }
}
