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
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.Color

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
                Box(Modifier.background(MaterialTheme.colors.background)) { leftPane() }
                Spacer(Modifier.width(1.dp).fillMaxHeight().border(BorderStroke(1.dp, Color.getSplitterColor())))
                Box(Modifier.background(MaterialTheme.colors.background)) { rightPane() }
            }
            Spacer(Modifier.height(1.dp).fillMaxWidth().border(BorderStroke(1.dp, Color.getSplitterColor())))
            Box(Modifier.background(MaterialTheme.colors.background)) { notificationArea() }
        }
        dialog()
    }
}

@Preview
@Composable
private fun ScreenLayout_Preview() {
    ScreenLayout(
        leftPane = {
            Box(Modifier.width(200.dp).fillMaxHeight().background(androidx.compose.ui.graphics.Color.Red))
        },
        rightPane = {
            Box(Modifier.fillMaxSize().background(androidx.compose.ui.graphics.Color.Blue))
        },
        notificationArea = {
            Box(Modifier.fillMaxWidth().height(50.dp).background(androidx.compose.ui.graphics.Color.Green))
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
            Box(Modifier.width(200.dp).fillMaxHeight().background(androidx.compose.ui.graphics.Color.LightGray))
        },
        rightPane = {
            Box(Modifier.fillMaxSize().background(androidx.compose.ui.graphics.Color.White))
        },
        notificationArea = {
            Box(Modifier.fillMaxWidth().height(50.dp).background(androidx.compose.ui.graphics.Color.Green))
        },
        dialog = {
        },
        modifier = Modifier.fillMaxSize()
    )
}
