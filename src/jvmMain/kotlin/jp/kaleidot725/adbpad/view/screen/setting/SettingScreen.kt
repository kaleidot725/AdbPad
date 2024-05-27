package jp.kaleidot725.adbpad.view.screen.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.setting.Appearance
import jp.kaleidot725.adbpad.view.component.FloatingDialog
import jp.kaleidot725.adbpad.view.component.RadioButtons
import jp.kaleidot725.adbpad.view.component.RunningIndicator
import jp.kaleidot725.adbpad.view.component.language.LanguageDropButton
import jp.kaleidot725.adbpad.view.component.setting.SettingField
import jp.kaleidot725.adbpad.view.component.setting.SettingHeader
import jp.kaleidot725.adbpad.view.component.setting.SettingTitle

@Composable
fun SettingScreen(
    languages: List<Language.Type>,
    selectLanguage: Language.Type,
    onUpdateLanguage: (Language.Type) -> Unit,
    appearance: Appearance,
    updateAppearance: (Appearance) -> Unit,
    adbDirectoryPath: String,
    onChangeAdbDirectoryPath: (String) -> Unit,
    isValidAdbDirectoryPath: Boolean,
    adbPortNumber: String,
    onChangeAdbPortNumber: (String) -> Unit,
    isValidAdbPortNumber: Boolean,
    onRestartAdb: () -> Unit,
    isRestartingAdb: Boolean,
    onSave: () -> Unit,
    canSave: Boolean,
    onCancel: () -> Unit,
) {
    FloatingDialog(
        modifier =
            Modifier
                .width(960.dp)
                .fillMaxHeight()
                .padding(vertical = 32.dp),
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                SettingHeader(modifier = Modifier.fillMaxWidth())

                Divider(modifier = Modifier.fillMaxWidth())

                SettingTitle(
                    text = Language.settingLanguageHeader,
                    modifier = Modifier.padding(horizontal = 4.dp),
                )

                LanguageDropButton(
                    languages = languages,
                    selectedLanguage = selectLanguage,
                    onSelect = onUpdateLanguage,
                    modifier = Modifier.padding(horizontal = 4.dp),
                )

                Divider(modifier = Modifier.fillMaxWidth())

                SettingTitle(
                    text = Language.settingAppearanceHeader,
                    modifier = Modifier.padding(horizontal = 4.dp),
                )

                RadioButtons(
                    selectedItem = appearance.value,
                    items = Appearance.entries.map { it.value },
                    onSelect = { value -> updateAppearance(Appearance.entries.first { it.value == value }) },
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                )

                Divider(modifier = Modifier.fillMaxWidth())

                SettingTitle(
                    text = Language.settingAdbHeader,
                    modifier = Modifier.padding(horizontal = 4.dp),
                )

                SettingField(
                    title = Language.settingAdbDirectoryPathTitle,
                    input = adbDirectoryPath,
                    isError = !isValidAdbDirectoryPath,
                    onValueChange = onChangeAdbDirectoryPath,
                )

                SettingField(
                    title = Language.settingAdbPortNumberTitle,
                    input = adbPortNumber,
                    isError = !isValidAdbPortNumber,
                    onValueChange = onChangeAdbPortNumber,
                )

                Button(
                    onClick = onRestartAdb,
                    modifier =
                        Modifier
                            .widthIn(min = 150.dp)
                            .align(Alignment.End),
                ) {
                    when {
                        isRestartingAdb -> RunningIndicator()
                        else ->
                            Text(
                                text = Language.settingAdbRestartTitle,
                                textAlign = TextAlign.Center,
                            )
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.align(Alignment.BottomEnd),
            ) {
                Button(onClick = onCancel) {
                    Text(
                        text = Language.cancel,
                        modifier = Modifier.width(100.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Button(onClick = onSave, enabled = canSave) {
                    Text(
                        text = Language.save,
                        modifier = Modifier.width(100.dp),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}
