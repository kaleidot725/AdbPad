package jp.kaleidot725.adbpad.ui.screen.command.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.component.indicator.RunningIndicator

@Composable
fun CommandItemList(
    title: String,
    detail: String,
    isRunning: Boolean,
    canExecute: Boolean,
    onExecute: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = detail,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Button(
                onClick = { onExecute() },
                enabled = canExecute,
                modifier = Modifier.width(100.dp),
            ) {
                Box(modifier = Modifier.width(60.dp), contentAlignment = Alignment.Center) {
                    when {
                        isRunning -> RunningIndicator()
                        else -> Text(text = Language.execute)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CommandItemList_Running_Preview() {
    CommandItemList(
        title = "ダークテーマON",
        detail = "端末のダークテーマ設定をONにします",
        isRunning = true,
        canExecute = true,
        onExecute = {},
        modifier = Modifier.fillMaxWidth().padding(16.dp),
    )
}

@Preview
@Composable
private fun CommandItemList_NotRunning_Preview() {
    CommandItemList(
        title = "ダークテーマON",
        detail = "端末のダークテーマ設定をONにします",
        isRunning = false,
        canExecute = true,
        onExecute = {},
        modifier = Modifier.fillMaxWidth().padding(16.dp),
    )
}

@Preview
@Composable
private fun CommandItemList_NotExecute_Preview() {
    CommandItemList(
        title = "ダークテーマON",
        detail = "端末のダークテーマ設定をONにします",
        isRunning = false,
        canExecute = false,
        onExecute = {},
        modifier = Modifier.fillMaxWidth().padding(16.dp),
    )
}
