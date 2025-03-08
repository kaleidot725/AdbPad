package jp.kaleidot725.adbpad.ui.screen.setting.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.kaleidot725.adbpad.ui.component.DefaultOutlineTextField

@Composable
fun SettingField(
    title: String,
    initialInput: String,
    isError: Boolean,
    onValueChange: (String) -> Unit,
) {
    DefaultOutlineTextField(
        initialText = initialInput,
        onUpdateText = onValueChange,
        label = title,
        modifier = Modifier.fillMaxWidth(),
        isError = isError,
        placeHolder = "",
    )
}

@Preview
@Composable
private fun SettingField_Preview() {
    SettingField("TITLE", "INPUT", false) {}
}
