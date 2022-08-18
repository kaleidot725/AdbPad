package jp.kaleidot725.adbpad.view.layout

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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

@Preview
@Composable
private fun AppLayout_Preview() {
    AppLayout(
        leftPane = {
            Box(Modifier.width(200.dp).fillMaxHeight().background(Color.Red))
        },
        rightPane = {
            Box(Modifier.fillMaxSize().background(Color.Blue))
        },
        dialog = {},
        enableDialog = false,
        modifier = Modifier.fillMaxSize()
    )
}

@Preview
@Composable
private fun AppLayoutDialog_Preview() {
    AppLayout(
        leftPane = {
            Box(Modifier.width(200.dp).fillMaxHeight().background(Color.Red))
        },
        rightPane = {
            Box(Modifier.fillMaxSize().background(Color.Blue))
        },
        dialog = {
            Box(Modifier.fillMaxSize().padding(32.dp).background(Color.Green))
        },
        enableDialog = true,
        modifier = Modifier.fillMaxSize()
    )
}
