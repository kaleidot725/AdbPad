package jp.kaleidot725.adbpad.ui.screen.command.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Copy
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Play
import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandCategory
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.component.indicator.RunningIndicator
import jp.kaleidot725.adbpad.ui.component.layout.ExpandableSection

@Composable
fun CommandDetailPane(
    command: NormalCommand,
    canExecute: Boolean,
    onExecute: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
    ) {
        // スクロール可能なコンテンツエリア
        Column(
            modifier =
                Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // プロパティセクション
            ExpandableSection(title = Language.commandPropertySection) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    // タイトル
                    Text(
                        text = Language.commandDetailTitle,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text = command.title,
                        style = MaterialTheme.typography.bodyMedium,
                    )

                    // 説明
                    Text(
                        text = Language.commandDetailDescription,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text = command.details,
                        style = MaterialTheme.typography.bodyMedium,
                    )

                    // カテゴリ
                    Text(
                        text = Language.commandDetailCategory,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text =
                            when (command.category) {
                                NormalCommandCategory.UI -> Language.categoryUI
                                NormalCommandCategory.COM -> Language.categoryCommunication
                                NormalCommandCategory.ALL -> Language.categoryAll
                            },
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }

            // コマンドセクション
            ExpandableSection(title = Language.commandsSection) {
                CommandsContent(command = command)
            }
        }

        // 実行ボタン
        CommandActionButton(
            text = Language.execute,
            icon = Lucide.Play,
            onClick = onExecute,
            enabled = canExecute,
            isRunning = command.isRunning,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
        )
    }
}

@Composable
private fun CommandsContent(command: NormalCommand) {
    val clipboardManager = LocalClipboardManager.current
    val commandsText = command.commandStrings.joinToString("\n") { "adb shell $it" }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = Language.copy,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            IconButton(
                onClick = {
                    clipboardManager.setText(AnnotatedString(commandsText))
                },
                modifier = Modifier.size(32.dp),
            ) {
                Icon(
                    imageVector = Lucide.Copy,
                    contentDescription = Language.copy,
                    modifier = Modifier.size(16.dp),
                )
            }
        }

        SelectionContainer {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                command.commandStrings.forEach { commandString ->
                    Text(
                        text = "adb shell $commandString",
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = FontFamily.Monospace,
                    )
                }
            }
        }
    }
}

@Composable
private fun CommandActionButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    enabled: Boolean,
    isRunning: Boolean,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        enabled = enabled && !isRunning,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = modifier,
    ) {
        if (isRunning) {
            RunningIndicator(modifier = Modifier.size(16.dp))
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                )
                Text(text = text)
            }
        }
    }
}
