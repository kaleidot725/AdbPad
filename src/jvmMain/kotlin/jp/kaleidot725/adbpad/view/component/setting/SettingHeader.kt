package jp.kaleidot725.adbpad.view.component.setting

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.Language

@Composable
fun SettingHeader(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            text = Language.SETTING,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 4.dp).weight(1.0f, true).align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
private fun SettingHeader_Preview() {
    SettingHeader()
}
