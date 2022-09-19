package jp.kaleidot725.adbpad.view.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.view.resource.ColorRes

@Composable
fun ScreenLayout(
    leftPane: @Composable () -> Unit,
    rightPane: @Composable () -> Unit,
    notificationArea: @Composable () -> Unit,
    dialog: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Column {
            Row(modifier = Modifier.weight(0.9f, true)) {
                Box(Modifier.background(ColorRes.LEFT_PANE)) { leftPane() }
                Spacer(Modifier.width(1.dp).fillMaxHeight().border(BorderStroke(1.dp, ColorRes.PANE_SPLITTER)))
                Box(Modifier.background(ColorRes.RIGHT_PANE)) { rightPane() }
            }
            Spacer(Modifier.height(1.dp).fillMaxWidth().border(BorderStroke(1.dp, ColorRes.PANE_SPLITTER)))
            Box(Modifier.background(ColorRes.NOTIFICATION_AREA)) { notificationArea() }
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
        notificationArea = {
            Box(Modifier.fillMaxWidth().height(50.dp).background(Color.Green))
        },
        dialog = {

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
        notificationArea = {
            Box(Modifier.fillMaxWidth().height(50.dp).background(Color.Green))
        },
        dialog = {

        },
        modifier = Modifier.fillMaxSize()
    )
}
