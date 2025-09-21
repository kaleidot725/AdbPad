package jp.kaleidot725.adbpad.ui.screen.text.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Check
import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.common.dummy.TextCommandDummy
import jp.kaleidot725.adbpad.ui.common.resource.UserColor

@Composable
fun TextCommandActions(
    command: TextCommand,
    canSend: Boolean,
    onSendText: () -> Unit,
    selectedOption: TextCommand.Option,
    onUpdateTextCommandOption: (TextCommand.Option) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
    ) {
        Spacer(
            modifier = Modifier.weight(1.0f),
        )

        Box {
            TextCommandButton(
                selectedOption = selectedOption,
                canSend = canSend,
                isSending = command.isRunning,
                onSend = onSendText,
                onChangeOption = { expanded = true },
                modifier = Modifier,
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier =
                    Modifier
                        .width(250.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(4.dp),
                        ).border(
                            width = 1.dp,
                            color = UserColor.getSplitterColor(),
                            shape = RoundedCornerShape(4.dp),
                        ),
            ) {
                TextCommand.Option.entries.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Box(
                                    modifier = Modifier.padding(top = 8.dp).size(20.dp).align(Alignment.CenterVertically),
                                ) {
                                    if (selectedOption == option) {
                                        Icon(
                                            imageVector = Lucide.Check,
                                            contentDescription = "",
                                            modifier = Modifier.fillMaxSize(),
                                        )
                                    }
                                }

                                Text(
                                    text =
                                        when (option) {
                                            TextCommand.Option.SendWithTab -> Language.textCommandOptionTab
                                            TextCommand.Option.SendWithNewLine -> Language.textCommandOptionNewLine
                                        },
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                )
                            }
                        },
                        onClick = {
                            onUpdateTextCommandOption(option)
                            expanded = false
                        },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    TextCommandActions(
        command = TextCommandDummy.value,
        canSend = true,
        onSendText = {},
        selectedOption = TextCommand.Option.SendWithTab,
        onUpdateTextCommandOption = {},
    )
}
