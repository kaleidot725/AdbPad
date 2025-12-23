package jp.kaleidot725.adbpad.ui.screen.newdisplay.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.device.ScrcpyOptions
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.component.dropbox.EnumDropDown
import jp.kaleidot725.adbpad.ui.component.layout.ExpandableSection
import jp.kaleidot725.adbpad.ui.component.text.DefaultOutlineTextField
import jp.kaleidot725.scrcpykt.option.VideoCodec
import jp.kaleidot725.scrcpykt.option.VideoSource

@Composable
fun ScrcpyNewDisplayOptions(
    isEditable: Boolean,
    selectedProfileId: String?,
    inputWidth: String,
    inputHeight: String,
    inputDpi: String,
    scrcpyOptions: ScrcpyOptions,
    onUpdateInputWidth: (String) -> Unit,
    onUpdateInputHeight: (String) -> Unit,
    onUpdateInputDpi: (String) -> Unit,
    onUpdateScrcpyOptions: (ScrcpyOptions) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        // Virtual Display Options
        ExpandableSection(title = Language.virtualDisplayOptionsSection) {
            if (isEditable) {
                // Width
                DefaultOutlineTextField(
                    id = "width-$selectedProfileId",
                    initialText = inputWidth,
                    onUpdateText = onUpdateInputWidth,
                    label = Language.virtualDisplayWidthLabel,
                    placeHolder = "1080",
                    isError = false,
                    modifier = Modifier.fillMaxWidth(),
                )

                // Height
                DefaultOutlineTextField(
                    id = "height-$selectedProfileId",
                    initialText = inputHeight,
                    onUpdateText = onUpdateInputHeight,
                    label = Language.virtualDisplayHeightLabel,
                    placeHolder = "1920",
                    isError = false,
                    modifier = Modifier.fillMaxWidth(),
                )

                // DPI
                DefaultOutlineTextField(
                    id = "dpi-$selectedProfileId",
                    initialText = inputDpi,
                    onUpdateText = onUpdateInputDpi,
                    label = Language.virtualDisplayDpiLabel,
                    placeHolder = "440",
                    isError = false,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        // Video Options
        ExpandableSection(title = Language.videoOptionsSection) {
            // Max Size
            DefaultOutlineTextField(
                id = "maxSize",
                initialText = scrcpyOptions.maxSize?.toString() ?: "",
                onUpdateText = { text ->
                    onUpdateScrcpyOptions(scrcpyOptions.copy(maxSize = text.toIntOrNull()))
                },
                label = Language.maxSizeLabel,
                placeHolder = "1920",
                isError = false,
                modifier = Modifier.fillMaxWidth(),
            )

            // Video Bit Rate
            DefaultOutlineTextField(
                id = "videoBitRate",
                initialText = scrcpyOptions.videoBitRate?.toString() ?: "",
                onUpdateText = { text ->
                    onUpdateScrcpyOptions(scrcpyOptions.copy(videoBitRate = text.toIntOrNull()))
                },
                label = Language.videoBitRateLabel,
                placeHolder = "8000000",
                isError = false,
                modifier = Modifier.fillMaxWidth(),
            )

            // Max FPS
            DefaultOutlineTextField(
                id = "maxFps",
                initialText = scrcpyOptions.maxFps?.toString() ?: "",
                onUpdateText = { text ->
                    onUpdateScrcpyOptions(scrcpyOptions.copy(maxFps = text.toIntOrNull()))
                },
                label = Language.maxFpsLabel,
                placeHolder = "60",
                isError = false,
                modifier = Modifier.fillMaxWidth(),
            )

            // Video Codec Dropdown
            EnumDropDown(
                selectedValue = scrcpyOptions.videoCodec,
                values = VideoCodec.entries.toList(),
                onValueSelected = { codec ->
                    onUpdateScrcpyOptions(scrcpyOptions.copy(videoCodec = codec))
                },
                displayName = { it?.value ?: Language.autoLabel },
                label = Language.videoCodecLabel,
                modifier = Modifier.fillMaxWidth(),
            )

            // Video Source Dropdown
            EnumDropDown(
                selectedValue = scrcpyOptions.videoSource,
                values = VideoSource.entries.toList(),
                onValueSelected = { source ->
                    onUpdateScrcpyOptions(scrcpyOptions.copy(videoSource = source))
                },
                displayName = { it?.value ?: Language.autoLabel },
                label = Language.videoSourceLabel,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        // Display Options
        ExpandableSection(title = Language.displayOptionsSection) {
            // Window X
            DefaultOutlineTextField(
                id = "windowX",
                initialText = scrcpyOptions.windowX?.toString() ?: "",
                onUpdateText = { text ->
                    onUpdateScrcpyOptions(scrcpyOptions.copy(windowX = text.toIntOrNull()))
                },
                label = Language.windowXLabel,
                placeHolder = "100",
                isError = false,
                modifier = Modifier.fillMaxWidth(),
            )

            // Window Y
            DefaultOutlineTextField(
                id = "windowY",
                initialText = scrcpyOptions.windowY?.toString() ?: "",
                onUpdateText = { text ->
                    onUpdateScrcpyOptions(scrcpyOptions.copy(windowY = text.toIntOrNull()))
                },
                label = Language.windowYLabel,
                placeHolder = "100",
                isError = false,
                modifier = Modifier.fillMaxWidth(),
            )

            // Window Width
            DefaultOutlineTextField(
                id = "windowWidth",
                initialText = scrcpyOptions.windowWidth?.toString() ?: "",
                onUpdateText = { text ->
                    onUpdateScrcpyOptions(scrcpyOptions.copy(windowWidth = text.toIntOrNull()))
                },
                label = Language.windowWidthLabel,
                placeHolder = "800",
                isError = false,
                modifier = Modifier.fillMaxWidth(),
            )

            // Window Height
            DefaultOutlineTextField(
                id = "windowHeight",
                initialText = scrcpyOptions.windowHeight?.toString() ?: "",
                onUpdateText = { text ->
                    onUpdateScrcpyOptions(scrcpyOptions.copy(windowHeight = text.toIntOrNull()))
                },
                label = Language.windowHeightLabel,
                placeHolder = "600",
                isError = false,
                modifier = Modifier.fillMaxWidth(),
            )

            // Boolean Options
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Checkbox(
                        checked = scrcpyOptions.fullscreen,
                        onCheckedChange = { onUpdateScrcpyOptions(scrcpyOptions.copy(fullscreen = it)) },
                    )
                    Text(
                        Language.fullscreenLabel,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Checkbox(
                        checked = scrcpyOptions.alwaysOnTop,
                        onCheckedChange = { onUpdateScrcpyOptions(scrcpyOptions.copy(alwaysOnTop = it)) },
                    )
                    Text(
                        Language.alwaysOnTopLabel,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ScrcpyNewDisplayOptionsPreview() {
    ScrcpyNewDisplayOptions(
        isEditable = true,
        selectedProfileId = "sample",
        inputWidth = "1080",
        inputHeight = "1920",
        inputDpi = "440",
        scrcpyOptions = ScrcpyOptions(),
        onUpdateInputWidth = {},
        onUpdateInputHeight = {},
        onUpdateInputDpi = {},
        onUpdateScrcpyOptions = {},
    )
}
