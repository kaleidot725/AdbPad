package jp.kaleidot725.adbpad.ui.screen

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
import jp.kaleidot725.adbpad.domain.model.UserColor

@Composable
fun ScreenLayout(
    top: @Composable () -> Unit,
    navigationRail: @Composable () -> Unit,
    content: @Composable () -> Unit,
    dialog: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        Column {
            top()
            Spacer(Modifier.height(1.dp).fillMaxWidth().border(BorderStroke(1.dp, UserColor.getSplitterColor())))
            Row(modifier = Modifier.weight(0.9f, true)) {
                Box(Modifier.background(MaterialTheme.colors.background)) { navigationRail() }
                Spacer(Modifier.width(1.dp).fillMaxHeight().border(BorderStroke(1.dp, UserColor.getSplitterColor())))
                Box(Modifier.background(MaterialTheme.colors.background)) { content() }
            }
            Spacer(Modifier.height(1.dp).fillMaxWidth().border(BorderStroke(1.dp, UserColor.getSplitterColor())))
        }
        dialog()
    }
}

@Preview
@Composable
private fun ScreenLayout_Preview() {
    ScreenLayout(
        top = {
            Box(Modifier.height(50.dp).fillMaxWidth().background(androidx.compose.ui.graphics.Color.Red))
        },
        navigationRail = {
            Box(Modifier.width(50.dp).fillMaxHeight().background(androidx.compose.ui.graphics.Color.Yellow))
        },
        content = {
            Box(Modifier.fillMaxSize().background(androidx.compose.ui.graphics.Color.Blue))
        },
        dialog = {
        },
        modifier = Modifier.fillMaxSize(),
    )
}
