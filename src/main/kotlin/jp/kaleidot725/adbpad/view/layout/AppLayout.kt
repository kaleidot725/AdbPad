package jp.kaleidot725.adbpad.view.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppLayout(
    leftPane: @Composable () -> Unit,
    rightPane: @Composable () -> Unit,
    dialog: @Composable () -> Unit,
    enableDialog: Boolean,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Row {
            leftPane()
            rightPane()
        }
        if (enableDialog) dialog()
    }
}
