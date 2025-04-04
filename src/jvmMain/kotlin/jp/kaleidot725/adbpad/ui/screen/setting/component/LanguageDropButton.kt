package jp.kaleidot725.adbpad.ui.screen.setting.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.ChevronDown
import com.composables.icons.lucide.Lucide
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground
import jp.kaleidot725.adbpad.ui.common.resource.defaultBorder

@Composable
fun LanguageDropButton(
    languages: List<Language.Type>,
    selectedLanguage: Language.Type,
    onSelect: (Language.Type) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier) {
        Row(
            modifier =
                Modifier
                    .width(200.dp)
                    .defaultBorder()
                    .clip(RoundedCornerShape(4.dp))
                    .clickableBackground()
                    .clickable { expanded = true }
                    .padding(vertical = 8.dp, horizontal = 12.dp),
        ) {
            Text(
                text = selectedLanguage.title(),
                modifier = Modifier.align(Alignment.CenterVertically).weight(1.0f),
            )

            Icon(
                imageVector = Lucide.ChevronDown,
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterVertically),
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(200.dp),
        ) {
            languages.forEach { language ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onSelect(language)
                    },
                ) {
                    Text(
                        text = language.title(),
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.align(Alignment.CenterVertically),
                    )
                }
            }
        }
    }
}

private fun Language.Type.title(): String =
    when (this) {
        Language.Type.ENGLISH -> Language.settingLanguageEnglish
        Language.Type.JAPANESE -> Language.settingLanguageJapanese
        Language.Type.CHINESE -> Language.settingLanguageChinese
        Language.Type.TURKISH -> Language.settingLanguageTurkish
    }

@Preview
@Composable
private fun LanguageDropButton_Preview() {
    LanguageDropButton(Language.Type.values().toList(), Language.Type.ENGLISH, {})
}
