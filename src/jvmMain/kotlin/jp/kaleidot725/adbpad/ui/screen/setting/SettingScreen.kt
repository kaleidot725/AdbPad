package jp.kaleidot725.adbpad.ui.screen.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.setting.Appearance
import jp.kaleidot725.adbpad.ui.component.DefaultOutlineTextField
import jp.kaleidot725.adbpad.ui.component.FloatingDialog
import jp.kaleidot725.adbpad.ui.component.RadioButtons
import jp.kaleidot725.adbpad.ui.screen.setting.component.LanguageDropButton
import jp.kaleidot725.adbpad.ui.screen.setting.component.SettingHeader
import jp.kaleidot725.adbpad.ui.screen.setting.component.SettingTitle

@Composable
fun SettingScreen(
    initialized: Boolean,
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
    onSave: () -> Unit,
    canSave: Boolean,
    isSaving: Boolean,
    onCancel: () -> Unit,
    canCancel: Boolean,
) {
    FloatingDialog(
        modifier =
            Modifier
                .width(960.dp)
                .fillMaxHeight()
                .padding(vertical = 32.dp),
    ) {
        if (!initialized) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
            return@FloatingDialog
        }

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

                DefaultOutlineTextField(
                    id = initialized,
                    initialText = adbDirectoryPath,
                    onUpdateText = onChangeAdbDirectoryPath,
                    label = Language.settingAdbDirectoryPathTitle,
                    modifier = Modifier.fillMaxWidth(),
                    isError = !isValidAdbDirectoryPath,
                    placeHolder = "",
                )

                DefaultOutlineTextField(
                    id = initialized,
                    initialText = adbPortNumber,
                    onUpdateText = onChangeAdbPortNumber,
                    label = Language.settingAdbPortNumberTitle,
                    modifier = Modifier.fillMaxWidth(),
                    isError = !isValidAdbPortNumber,
                    placeHolder = "",
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.align(Alignment.BottomEnd),
            ) {
                Button(
                    onClick = onCancel,
                    enabled = canCancel,
                ) {
                    Text(
                        text = Language.cancel,
                        modifier = Modifier.width(100.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Button(
                    onClick = onSave,
                    enabled = canSave,
                ) {
                    if (isSaving) {
                        Box(
                            modifier = Modifier.width(100.dp),
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(12.dp).align(Alignment.Center),
                            )
                        }
                    } else {
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
}
