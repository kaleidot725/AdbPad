package jp.kaleidot725.adbpad.ui.screen.newdisplay

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isFinite
import com.composables.icons.lucide.Lucide
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.device.DeviceState
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.scrcpy.ScrcpyNewDisplayProfile
import jp.kaleidot725.adbpad.domain.model.sort.SortType
import jp.kaleidot725.adbpad.ui.common.resource.UserColor
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground
import jp.kaleidot725.adbpad.ui.common.resource.defaultBorder
import jp.kaleidot725.adbpad.ui.component.dropbox.SearchSortDropBox
import jp.kaleidot725.adbpad.ui.component.text.DefaultTextField
import jp.kaleidot725.adbpad.ui.screen.newdisplay.state.ScrcpyNewDisplayAction
import jp.kaleidot725.adbpad.ui.screen.newdisplay.state.ScrcpyNewDisplayState
import jp.kaleidot725.adbpad.ui.screen.screenshot.cursorForHorizontalResize
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.HorizontalSplitPane
import org.jetbrains.compose.splitpane.rememberSplitPaneState
import java.util.Locale
import kotlin.math.max
import kotlin.math.min

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
fun ScrcpyNewDisplayScreen(
    state: ScrcpyNewDisplayState,
    onAction: (ScrcpyNewDisplayAction) -> Unit,
) {
    val splitPaneState = rememberSplitPaneState()
    val filteredProfiles = state.filteredProfiles
    val maxProfileDimension =
        remember(state.profiles) {
            state.profiles.maxOfOrNull { max(it.width, it.height) }?.toFloat() ?: 1f
        }

    HorizontalSplitPane(
        splitPaneState = splitPaneState,
        modifier = Modifier.fillMaxSize(),
    ) {
        first(minSize = 350.dp) {
            Box(
                modifier =
                    Modifier
                        .fillMaxSize(),
            ) {
                Column(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(bottom = 88.dp),
                ) {
                    ScrcpyNewDisplayHeader(
                        searchText = state.searchText,
                        sortType = state.sortType,
                        onUpdateSearchText = { onAction(ScrcpyNewDisplayAction.UpdateSearchText(it)) },
                        onUpdateSortType = { onAction(ScrcpyNewDisplayAction.UpdateSortType(it)) },
                    )

                    Divider(modifier = Modifier.fillMaxWidth().defaultBorder())

                    if (filteredProfiles.isEmpty()) {
                        Box(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                        ) {
                            Text(
                                text = Language.scrcpyNewDisplayEmpty,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.align(Alignment.Center),
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth().weight(1f),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                        ) {
                            items(filteredProfiles, key = { it.id }) { profile ->
                                val selected = state.selectedProfile?.id == profile.id
                                ProfileListItem(
                                    profile = profile,
                                    selected = selected,
                                    onClick = { onAction(ScrcpyNewDisplayAction.SelectProfile(profile.id)) },
                                )
                            }
                        }
                    }
                }

            }
        }

        second {
            ScrcpyNewDisplayDetailPanel(
                profile = state.selectedProfile,
                selectedDevice = state.selectedDevice,
                maxProfileDimension = maxProfileDimension,
                isLaunching = state.isLaunching,
                canLaunch = state.canLaunch,
                onLaunch = { onAction(ScrcpyNewDisplayAction.LaunchSelectedProfile) },
            )
        }

        splitter {
            visiblePart {
                Box(
                    Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                        .background(UserColor.getSplitterColor()),
                )
            }

            handle {
                Box(
                    Modifier
                        .markAsHandle()
                        .cursorForHorizontalResize()
                        .width(10.dp)
                        .fillMaxHeight(),
                )
            }
        }
    }
}

@Composable
private fun ScrcpyNewDisplayHeader(
    searchText: String,
    sortType: SortType,
    onUpdateSearchText: (String) -> Unit,
    onUpdateSortType: (SortType) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        SearchSortDropBox(
            selectedSortType = sortType,
            onSelectType = onUpdateSortType,
        )

        DefaultTextField(
            initialText = searchText,
            onUpdateText = onUpdateSearchText,
            placeHolder = Language.search,
            modifier = Modifier.align(Alignment.CenterVertically).weight(1f),
        )
    }
}

@Composable
private fun ScrcpyNewDisplayActions(
    isLaunching: Boolean,
    canLaunch: Boolean,
    onLaunch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {
        Button(
            onClick = onLaunch,
            enabled = canLaunch && !isLaunching,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        ) {
            if (isLaunching) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp,
                )
            } else {
                Text(text = Language.execute)
            }
        }
    }
}

@Composable
private fun ScrcpyNewDisplayDetailPanel(
    profile: ScrcpyNewDisplayProfile?,
    selectedDevice: Device?,
    maxProfileDimension: Float,
    isLaunching: Boolean,
    canLaunch: Boolean,
    onLaunch: () -> Unit,
) {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
    ) {
        if (profile != null) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 48.dp, vertical = 40.dp),
            ) {
                DeviceMockup(
                    profile = profile,
                    maxDimension = maxProfileDimension,
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(24.dp))

                ProfileDetailsCard(profile = profile)

                Spacer(modifier = Modifier.weight(1f, fill = true))

                ScrcpyNewDisplayActions(
                    isLaunching = isLaunching,
                    canLaunch = canLaunch,
                    onLaunch = onLaunch,
                    modifier = Modifier.fillMaxWidth(),
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

@Composable
private fun ProfileDetailsCard(
    profile: ScrcpyNewDisplayProfile,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .widthIn(max = 480.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f))
                    .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                    .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(
                text = "${formatEnumLabel(profile.brand.name)} • ${formatEnumLabel(profile.formFactor.name)}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            DetailRow(
                label = Language.scrcpyNewDisplayResolution,
                value = "${profile.width} × ${profile.height}",
            )

            DetailRow(
                label = Language.scrcpyNewDisplayDensity,
                value = profile.densityDpi?.let { "$it dpi" } ?: Language.scrcpyNewDisplayDensityUnknown,
            )

            profile.note?.let { note ->
                DetailRow(
                    label = Language.scrcpyNewDisplayPanel,
                    value = note,
                )
            }
        }
    }
}

@Composable
private fun DetailRow(
    label: String,
    value: String,
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
private fun ProfileListItem(
    profile: ScrcpyNewDisplayProfile,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickableBackground(
                    isSelected = selected,
                    shape = RoundedCornerShape(6.dp),
                ).clickable(onClick = onClick)
                .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = profile.displayName,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
            )

            Text(
                text = "${formatEnumLabel(profile.brand.name)} • ${formatEnumLabel(profile.formFactor.name)}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Text(
                text = profile.shortSpec,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            profile.note?.let { note ->
                Text(
                    text = note,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

private fun formatEnumLabel(name: String): String {
    val normalized = name.lowercase(Locale.getDefault()).replace('_', ' ')
    return normalized.replaceFirstChar { it.titlecase(Locale.getDefault()) }
}

@Composable
private fun DeviceMockup(
    profile: ScrcpyNewDisplayProfile,
    maxDimension: Float,
    modifier: Modifier = Modifier,
) {
    val bodyShape = MaterialTheme.shapes.large
    val screenShape = MaterialTheme.shapes.medium
    val frameColor = MaterialTheme.colorScheme.surfaceVariant
    val screenColor = MaterialTheme.colorScheme.surface
    val detailColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.15f)

    val maxDimensionSafe = max(maxDimension, 1f)
    val widthPx = profile.width.coerceAtLeast(1)
    val heightPx = profile.height.coerceAtLeast(1)

    BoxWithConstraints(
        modifier = modifier.fillMaxWidth().fillMaxHeight(),
        contentAlignment = Alignment.Center,
    ) {
        val maxWidthDp = maxWidth
        val maxHeightDp = if (maxHeight.isFinite) maxHeight else 380.dp

        val longestSide = max(widthPx, heightPx).toFloat()
        val relativeScale = (longestSide / maxDimensionSafe).coerceIn(0.25f, 1f)
        val boundingWidthDp = maxWidthDp * relativeScale
        val boundingHeightDp = maxHeightDp * relativeScale

        val widthScale = boundingWidthDp.value / widthPx
        val heightScale = boundingHeightDp.value / heightPx
        val scale = min(widthScale, heightScale)

        val deviceWidthDp = (widthPx * scale).dp
        val deviceHeightDp = (heightPx * scale).dp

        Box(
            modifier =
                Modifier
                    .size(deviceWidthDp, deviceHeightDp)
                    .clip(bodyShape)
                    .background(frameColor)
                    .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f), bodyShape)
                    .padding(16.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier =
                        Modifier
                            .width(72.dp * relativeScale)
                            .height(6.dp * relativeScale)
                            .clip(RoundedCornerShape(3.dp))
                            .background(detailColor),
                )

                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .clip(screenShape)
                            .background(screenColor)
                            .border(1.dp, detailColor, screenShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "${profile.height} × ${profile.width}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp * relativeScale)) {
                    Box(
                        modifier =
                            Modifier
                                .width(32.dp * relativeScale)
                                .height(6.dp * relativeScale)
                                .clip(RoundedCornerShape(3.dp))
                                .background(detailColor),
                    )
                    Box(
                        modifier =
                            Modifier
                                .width(16.dp * relativeScale)
                                .height(6.dp * relativeScale)
                                .clip(RoundedCornerShape(3.dp))
                                .background(detailColor),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ScrcpyNewDisplayScreenPreview() {
    val sampleProfiles =
        remember {
            jp.kaleidot725.adbpad.domain.model.scrcpy
                .defaultScrcpyNewDisplayProfiles()
        }
    val state by remember {
        mutableStateOf(
            ScrcpyNewDisplayState(
                profiles = sampleProfiles,
                selectedProfileId = sampleProfiles.first().id,
                selectedDevice = Device(serial = "X1", name = "Pixel 8", state = DeviceState.UNKNOWN),
            ),
        )
    }
    ScrcpyNewDisplayScreen(state = state, onAction = {})
}
