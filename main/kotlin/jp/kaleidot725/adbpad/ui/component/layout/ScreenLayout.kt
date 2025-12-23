package jp.kaleidot725.adbpad.ui.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.ui.common.resource.UserColor

@Composable
fun ScreenLayout(
    top: (@Composable () -> Unit)? = null,
    navigationRail: @Composable () -> Unit,
    content: @Composable () -> Unit,
    right: @Composable () -> Unit,
    dialog: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        Column {
            if (top != null) {
                top()
                HorizontalDivider(color = UserColor.getSplitterColor())
            }
            Row(modifier = Modifier.weight(0.9f, true)) {
                Box(Modifier.background(MaterialTheme.colorScheme.background)) { navigationRail() }
                androidx.compose.material3.VerticalDivider(
                    modifier = Modifier.fillMaxHeight(),
                    color = UserColor.getSplitterColor(),
                )
                Box(Modifier.background(MaterialTheme.colorScheme.background).weight(1f)) { content() }
                androidx.compose.material3.VerticalDivider(
                    modifier = Modifier.fillMaxHeight(),
                    color = UserColor.getSplitterColor(),
                )
                Box(Modifier.background(MaterialTheme.colorScheme.background)) { right() }
            }
            HorizontalDivider(color = UserColor.getSplitterColor())
        }
        dialog()
    }
}

@Preview
@Composable
private fun ScreenLayout_Preview() {
    ScreenLayout(
        navigationRail = {
            Box(Modifier.width(50.dp).fillMaxHeight().background(androidx.compose.ui.graphics.Color.Yellow))
        },
        content = {
            Box(Modifier.fillMaxSize().background(androidx.compose.ui.graphics.Color.Blue))
        },
        right = {
            Box(Modifier.width(60.dp).fillMaxHeight().background(androidx.compose.ui.graphics.Color.Green))
        },
        dialog = {
        },
        modifier = Modifier.fillMaxSize(),
    )
}
