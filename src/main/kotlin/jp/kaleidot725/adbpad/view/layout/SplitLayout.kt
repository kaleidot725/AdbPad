package jp.kaleidot725.adbpad.view.layout

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.kaleidot725.adbpad.component.layout.VerticalBorder

@Composable
fun SplitLayout(
    leftPane: @Composable () -> Unit,
    rightPane: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        leftPane()
        VerticalBorder()
        rightPane()
    }
}
