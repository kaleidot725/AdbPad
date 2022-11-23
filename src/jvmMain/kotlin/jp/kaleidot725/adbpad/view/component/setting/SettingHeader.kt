package jp.kaleidot725.adbpad.view.component.setting

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.Language

@Composable
fun SettingHeader(
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            text = Language.SETTING,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 4.dp).weight(1.0f, true).align(Alignment.CenterVertically)
        )

        IconButton(
            onClick = onClose,
            modifier = Modifier.align(Alignment.CenterVertically).size(24.dp)
        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
        }
    }
}

@Preview
@Composable
private fun SettingHeader_Preview() {
    SettingHeader({}, Modifier)
}