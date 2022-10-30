package jp.kaleidot725.adbpad.view.component.command

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.view.common.resource.StringRes
import jp.kaleidot725.adbpad.view.component.RunningIndicator

@Composable
fun CommandItem(
    title: String,
    detail: String,
    isRunning: Boolean,
    canExecute: Boolean,
    onExecute: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier, elevation = 1.dp) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = title)
            Text(text = detail, modifier = Modifier.weight(0.9f, true))
            Button(onClick = { onExecute() }, enabled = canExecute, modifier = Modifier.align(Alignment.End)) {
            when {
                isRunning -> RunningIndicator()
                else -> Text(text = StringRes.EXECUTE)
            }
        }
        }
    }
}

@Preview
@Composable
private fun CommandItem_Running_Preview() {
    CommandItem(
        title = "ダークテーマON",
        detail = "端末のダークテーマ設定をONにします",
        isRunning = true,
        canExecute = true,
        onExecute = {},
        modifier = Modifier.height(200.dp).wrapContentWidth().padding(16.dp)
    )
}

@Preview
@Composable
private fun CommandItem_NotRunning_Preview() {
    CommandItem(
        title = "ダークテーマON",
        detail = "端末のダークテーマ設定をONにします",
        isRunning = false,
        canExecute = true,
        onExecute = {},
        modifier = Modifier.height(200.dp).wrapContentWidth().padding(16.dp)
    )
}

@Preview
@Composable
private fun CommandItem_NotExecute_Preview() {
    CommandItem(
        title = "ダークテーマON",
        detail = "端末のダークテーマ設定をONにします",
        isRunning = false,
        canExecute = false,
        onExecute = {},
        modifier = Modifier.height(200.dp).wrapContentWidth().padding(16.dp)
    )
}
