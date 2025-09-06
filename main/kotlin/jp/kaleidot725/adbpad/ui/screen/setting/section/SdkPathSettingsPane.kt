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
import jp.kaleidot725.adbpad.ui.component.text.DefaultOutlineTextField
import jp.kaleidot725.adbpad.ui.component.text.SubTitle

@Composable
fun SdkPathSettingsPane(
    initialized: Boolean,
    adbDirectoryPath: String,
    onChangeAdbDirectoryPath: (String) -> Unit,
    isValidAdbDirectoryPath: Boolean,
    adbPortNumber: String,
    onChangeAdbPortNumber: (String) -> Unit,
    isValidAdbPortNumber: Boolean,
    scrcpyBinaryPath: String,
    onChangeScrcpyBinaryPath: (String) -> Unit,
    isValidScrcpyBinaryPath: Boolean,
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

        SubTitle(
            text = Language.settingScrcpyHeader,
            modifier = Modifier.padding(horizontal = 4.dp),
        )

        DefaultOutlineTextField(
            id = initialized,
            initialText = scrcpyBinaryPath,
            onUpdateText = onChangeScrcpyBinaryPath,
            label = Language.settingScrcpyBinaryPathTitle,
            modifier = Modifier.fillMaxWidth(),
            isError = !isValidScrcpyBinaryPath,
            placeHolder = "",
        )
    }
}
