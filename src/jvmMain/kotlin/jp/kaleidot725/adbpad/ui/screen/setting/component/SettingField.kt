package jp.kaleidot725.adbpad.ui.screen.setting.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingField(
    title: String,
    input: String,
    isError: Boolean,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = input,
        onValueChange = onValueChange,
        label = { Text(title) },
        modifier = Modifier.fillMaxWidth(),
        isError = isError,
    )
}

@Preview
@Composable
private fun SettingField_Preview() {
    SettingField("TITLE", "INPUT", false) {}
}
