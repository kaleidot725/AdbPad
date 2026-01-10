package jp.kaleidot725.adbpad.ui.screen.command.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground
import jp.kaleidot725.adbpad.ui.component.indicator.RunningIndicator

@Composable
fun CommandItemCard(
    title: String,
    detail: String,
    isRunning: Boolean,
    canExecute: Boolean,
    onExecute: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        tonalElevation = 2.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .clickableBackground(isSelected = isRunning, shape = RoundedCornerShape(6.dp))
                    .padding(horizontal = 12.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = if (isRunning) FontWeight.SemiBold else FontWeight.Normal,
            )

            Text(
                text = detail,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 3,
                overflow = TextOverflow.Clip,
            )

            Spacer(modifier = Modifier.weight(1f, fill = true))

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Button(
                    onClick = onExecute,
                    enabled = canExecute,
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                ) {
                    Box(
                        modifier = Modifier.width(60.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        if (isRunning) {
                            RunningIndicator(modifier = Modifier.size(16.dp))
                        } else {
                            Text(text = Language.execute)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CommandItemCard_Running_Preview() {
    CommandItemCard(
        title = "ダークテーマON",
        detail = "端末のダークテーマ設定をONにします",
        isRunning = true,
        canExecute = true,
        onExecute = {},
        modifier = Modifier.height(200.dp).wrapContentWidth().padding(16.dp),
    )
}

@Preview
@Composable
private fun CommandItemCard_NotRunning_Preview() {
    CommandItemCard(
        title = "ダークテーマON",
        detail = "端末のダークテーマ設定をONにします",
        isRunning = false,
        canExecute = true,
        onExecute = {},
        modifier = Modifier.height(200.dp).wrapContentWidth().padding(16.dp),
    )
}

@Preview
@Composable
private fun CommandItemCard_NotExecute_Preview() {
    CommandItemCard(
        title = "ダークテーマON",
        detail = "端末のダークテーマ設定をONにします",
        isRunning = false,
        canExecute = false,
        onExecute = {},
        modifier = Modifier.height(200.dp).wrapContentWidth().padding(16.dp),
    )
}
