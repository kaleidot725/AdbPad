package jp.kaleidot725.adbpad.ui.screen.setting.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.setting.AccentColor
import jp.kaleidot725.adbpad.domain.model.setting.Appearance
import jp.kaleidot725.adbpad.ui.component.button.RadioButtons
import jp.kaleidot725.adbpad.ui.component.text.SubTitle
import jp.kaleidot725.adbpad.ui.screen.setting.component.AccentColorDropButton
import jp.kaleidot725.adbpad.ui.screen.setting.component.LanguageDropButton

@Composable
fun AppearanceSettingsPane(
    languages: List<Language.Type>,
    selectedLanguage: Language.Type,
    onUpdateLanguage: (Language.Type) -> Unit,
    appearance: Appearance,
    onUpdateAppearance: (Appearance) -> Unit,
    accentColor: AccentColor,
    onUpdateAccentColor: (AccentColor) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        SubTitle(
            text = Language.settingLanguageHeader,
            modifier = Modifier.padding(horizontal = 4.dp),
        )

        LanguageDropButton(
            languages = languages,
            selectedLanguage = selectedLanguage,
            onSelect = onUpdateLanguage,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
        )

        SubTitle(
            text = Language.settingThemeHeader,
            modifier = Modifier.padding(horizontal = 4.dp),
        )

        RadioButtons(
            selectedItem = appearance.value,
            items = Appearance.entries.map { it.value },
            onSelect = { value -> onUpdateAppearance(Appearance.entries.first { it.value == value }) },
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
        )

        SubTitle(
            text = Language.settingAccentColorHeader,
            modifier = Modifier.padding(horizontal = 4.dp),
        )

        AccentColorDropButton(
            accentColors = AccentColor.entries,
            selectedAccentColor = accentColor,
            onSelect = onUpdateAccentColor,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
        )
    }
}
