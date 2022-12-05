package jp.kaleidot725.adbpad.view.component.language

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.language.Language

@Composable
fun LanguageDropButton(
    languages: List<Language.Type>,
    selectedLanguage: Language.Type,
    onSelect: (Language.Type) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier) {
        Row(
            modifier = Modifier
                .width(200.dp)
                .border(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colors.primary, width = 1.dp)
                .clickable { expanded = true }
                .padding(vertical = 8.dp, horizontal = 12.dp)
        ) {
            Text(
                text = selectedLanguage.title(),
                modifier = Modifier.align(Alignment.CenterVertically).weight(1.0f)
            )

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(200.dp)
        ) {
            languages.forEach { language ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onSelect(language)
                    }
                ) {
                    Text(
                        text = language.title(),
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}

private fun Language.Type.title(): String {
    return when (this) {
        Language.Type.ENGLISH -> Language.SETTING_LANGUAGE_ENGLISH
        Language.Type.JAPANESE -> Language.SETTING_LANGUAGE_JAPANESE
    }
}

@Preview
@Composable
private fun LanguageDropButton_Preview() {
    LanguageDropButton(Language.Type.values().toList(), Language.Type.ENGLISH, {})
}