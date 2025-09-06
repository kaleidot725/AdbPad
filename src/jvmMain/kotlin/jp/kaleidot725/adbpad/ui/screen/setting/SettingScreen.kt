package jp.kaleidot725.adbpad.ui.screen.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.component.button.FloatingDialog
import jp.kaleidot725.adbpad.ui.screen.setting.model.SettingCategory
import jp.kaleidot725.adbpad.ui.screen.setting.section.AppearanceSettingsPane
import jp.kaleidot725.adbpad.ui.screen.setting.section.CategorySidebar
import jp.kaleidot725.adbpad.ui.screen.setting.section.SdkPathSettingsPane
import jp.kaleidot725.adbpad.ui.screen.setting.state.SettingAction
import jp.kaleidot725.adbpad.ui.screen.setting.state.SettingState

@Composable
fun SettingScreen(
    state: SettingState,
    onAction: (SettingAction) -> Unit,
    onMainRefresh: () -> Unit,
) {
    FloatingDialog(
        modifier =
            Modifier
                .width(960.dp)
                .fillMaxHeight()
                .padding(vertical = 32.dp),
    ) {
        if (!state.initialized) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
            return@FloatingDialog
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxSize()) {
                CategorySidebar(
                    categories = SettingCategory.entries,
                    selectedCategory = state.selectedCategory,
                    onCategorySelected = { onAction(SettingAction.SelectCategory(it)) },
                )

                VerticalDivider()

                when (state.selectedCategory) {
                    SettingCategory.APPEARANCE -> {
                        AppearanceSettingsPane(
                            languages = state.languages,
                            selectedLanguage = state.selectedLanguage,
                            onUpdateLanguage = { onAction(SettingAction.UpdateLanguage(it)) },
                            appearance = state.appearance,
                            onUpdateAppearance = { onAction(SettingAction.UpdateAppearance(it)) },
                            accentColor = state.accentColor,
                            onUpdateAccentColor = { onAction(SettingAction.UpdateAccentColor(it)) },
                            modifier = Modifier.weight(1f),
                        )
                    }
                    SettingCategory.SDK -> {
                        SdkPathSettingsPane(
                            initialized = state.initialized,
                            adbDirectoryPath = state.adbDirectoryPath,
                            onChangeAdbDirectoryPath = { onAction(SettingAction.UpdateAdbDirectoryPath(it)) },
                            isValidAdbDirectoryPath = state.isValidAdbDirectoryPath,
                            adbPortNumber = state.adbPortNumber,
                            onChangeAdbPortNumber = { onAction(SettingAction.UpdateAdbPortNumberPath(it)) },
                            isValidAdbPortNumber = state.isValidAdbPortNumber,
                            scrcpyBinaryPath = state.scrcpyBinaryPath,
                            onChangeScrcpyBinaryPath = { onAction(SettingAction.UpdateScrcpyBinaryPath(it)) },
                            isValidScrcpyBinaryPath = state.isValidScrcpyBinaryPath,
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
            ) {
                Button(
                    onClick = onMainRefresh,
                    enabled = state.canCancel,
                ) {
                    Text(
                        text = Language.cancel,
                        modifier = Modifier.width(100.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Button(
                    onClick = { onAction(SettingAction.Save) },
                    enabled = state.canSave,
                ) {
                    if (state.isSaving) {
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
