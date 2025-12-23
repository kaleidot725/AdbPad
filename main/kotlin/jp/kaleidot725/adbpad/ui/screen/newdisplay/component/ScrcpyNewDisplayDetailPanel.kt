package jp.kaleidot725.adbpad.ui.screen.newdisplay.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.device.ScrcpyOptions
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.scrcpy.ScrcpyNewDisplayProfile
import jp.kaleidot725.adbpad.ui.common.resource.UserColor
import jp.kaleidot725.adbpad.ui.component.text.DefaultTextField

@Composable
fun ScrcpyNewDisplayDetailPanel(
    selectedProfile: ScrcpyNewDisplayProfile?,
    isEditable: Boolean,
    selectedProfileId: String?,
    inputName: String,
    inputWidth: String,
    inputHeight: String,
    inputDpi: String,
    scrcpyOptions: ScrcpyOptions,
    isLaunching: Boolean,
    canLaunch: Boolean,
    maxProfileDimension: Float,
    onUpdateInputName: (String) -> Unit,
    onUpdateInputWidth: (String) -> Unit,
    onUpdateInputHeight: (String) -> Unit,
    onUpdateInputDpi: (String) -> Unit,
    onUpdateScrcpyOptions: (ScrcpyOptions) -> Unit,
    onLaunch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val effectiveProfile =
        remember(selectedProfile, inputWidth, inputHeight, inputDpi) {
            selectedProfile?.let { profile ->
                val w = (if (isEditable) inputWidth.toIntOrNull() else null) ?: profile.width
                val h = (if (isEditable) inputHeight.toIntOrNull() else null) ?: profile.height
                val dpi = (if (isEditable) inputDpi.toIntOrNull() else null) ?: profile.densityDpi

                profile.copy(width = w, height = h, densityDpi = dpi)
            }
        }

    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
    ) {
        if (effectiveProfile != null) {
            Row(
                modifier = Modifier.fillMaxSize(),
            ) {
                Column(
                    modifier =
                        Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (isEditable) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            DefaultTextField(
                                id = "inputName-$selectedProfileId",
                                initialText = inputName,
                                onUpdateText = onUpdateInputName,
                                placeHolder = "profile name",
                                modifier = Modifier.weight(1.0f).height(48.dp).padding(horizontal = 12.dp),
                            )
                        }
                        HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = UserColor.getSplitterColor())
                    }

                    Box(
                        modifier = Modifier.weight(1f).padding(16.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        DeviceMockup(
                            width = effectiveProfile.width,
                            height = effectiveProfile.height,
                            maxDimension = maxProfileDimension,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }

                VerticalDivider(
                    modifier = Modifier.fillMaxHeight(),
                    color = UserColor.getSplitterColor(),
                )

                ScrcpyNewDisplayMenu(
                    isEditable = isEditable,
                    selectedProfileId = selectedProfileId,
                    inputWidth = inputWidth,
                    inputHeight = inputHeight,
                    inputDpi = inputDpi,
                    scrcpyOptions = scrcpyOptions,
                    isLaunching = isLaunching,
                    canLaunch = canLaunch,
                    onUpdateInputWidth = onUpdateInputWidth,
                    onUpdateInputHeight = onUpdateInputHeight,
                    onUpdateInputDpi = onUpdateInputDpi,
                    onUpdateScrcpyOptions = onUpdateScrcpyOptions,
                    onLaunch = onLaunch,
                    modifier =
                        Modifier
                            .width(300.dp)
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(16.dp),
                )
            }
        } else {
            Text(
                text = Language.scrcpyNewDisplayEmpty,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Preview
@Composable
private fun ScrcpyNewDisplayDetailPanelPreview() {
    val sampleProfile =
        ScrcpyNewDisplayProfile(
            id = "sample",
            displayName = "Sample Profile",
            width = 1080,
            height = 1920,
            densityDpi = 440,
            refreshRateHz = 60,
        )
    ScrcpyNewDisplayDetailPanel(
        selectedProfile = sampleProfile,
        isEditable = true,
        selectedProfileId = sampleProfile.id,
        inputName = "My Profile",
        inputWidth = "1080",
        inputHeight = "1920",
        inputDpi = "440",
        scrcpyOptions = ScrcpyOptions(),
        isLaunching = false,
        canLaunch = true,
        maxProfileDimension = 1920f,
        onUpdateInputName = {},
        onUpdateInputWidth = {},
        onUpdateInputHeight = {},
        onUpdateInputDpi = {},
        onUpdateScrcpyOptions = {},
        onLaunch = {},
    )
}
