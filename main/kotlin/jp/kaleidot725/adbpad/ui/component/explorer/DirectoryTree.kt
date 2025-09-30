package jp.kaleidot725.adbpad.ui.component.explorer

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import jp.kaleidot725.adbpad.domain.model.explorer.ExplorerData
import jp.kaleidot725.adbpad.domain.model.explorer.ExplorerDirectory
import jp.kaleidot725.adbpad.domain.model.explorer.ExplorerFile
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun DirectoryTree(
    directory: ExplorerDirectory,
    selectedFile: ExplorerFile,
    expandedDirs: List<ExplorerDirectory>,
    level: Int = 0,
    onClickArrow: (ExplorerDirectory) -> Unit = {},
    onClickFile: (ExplorerFile) -> Unit = { },
    modifier: Modifier = Modifier,
) {
    val isExpanded by rememberUpdatedState(expandedDirs.any { it.id == directory.id })

    Column(modifier = modifier) {
        DirectoryItem(
            directory = directory,
            isExpanded = isExpanded,
            onExpand = {
                onClickArrow.invoke(directory)
                onClickFile.invoke(directory)
            },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .clickableBackground(
                        isSelected = selectedFile.id == directory.id,
                        shape = RoundedCornerShape(12.dp),
                    ).clickable { onClickFile.invoke(directory) }
                    .padding(8.dp)
                    .padding(start = level * 24.dp),
        )

        AnimatedContent(
            targetState = isExpanded,
            modifier = Modifier,
        ) { state ->
            if (state) {
                Column {
                    directory.list.forEach {
                        when (it) {
                            is ExplorerData -> {
                                DataFileItem(
                                    dataFile = it,
                                    modifier =
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(4.dp)
                                            .clickableBackground(
                                                isSelected = selectedFile.id == it.id,
                                                shape = RoundedCornerShape(12.dp),
                                            ).clickable { onClickFile.invoke(it) }
                                            .padding(8.dp)
                                            .padding(start = (level + 1) * 24.dp),
                                )
                            }

                            is ExplorerDirectory -> {
                                DirectoryTree(
                                    directory = it,
                                    selectedFile = selectedFile,
                                    expandedDirs = expandedDirs,
                                    level = level + 1,
                                    onClickFile = onClickFile,
                                    onClickArrow = onClickArrow,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
