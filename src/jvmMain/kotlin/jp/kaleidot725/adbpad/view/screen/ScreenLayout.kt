package jp.kaleidot725.adbpad.view.template

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.view.resource.ColorRes

@Composable
fun ScreenLayout(
    leftPane: @Composable () -> Unit,
    rightPane: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Row {
            Surface(color = ColorRes.LEFT_PANE) { leftPane() }
            Spacer(Modifier.width(1.dp).fillMaxHeight().border(BorderStroke(1.dp, ColorRes.PANE_SPLITTER)))
            Surface(color = ColorRes.RIGHT_PANE) { rightPane() }
        }
    }
}

@Preview
@Composable
private fun ScreenLayout_Preview() {
    ScreenLayout(
        leftPane = {
            Box(Modifier.width(200.dp).fillMaxHeight().background(Color.Red))
        },
        rightPane = {
            Box(Modifier.fillMaxSize().background(Color.Blue))
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Preview
@Composable
private fun AppTemplateDialog_Preview() {
    ScreenLayout(
        leftPane = {
            Box(Modifier.width(200.dp).fillMaxHeight().background(Color.LightGray))
        },
        rightPane = {
            Box(Modifier.fillMaxSize().background(Color.White))
        },
        modifier = Modifier.fillMaxSize()
    )
}
