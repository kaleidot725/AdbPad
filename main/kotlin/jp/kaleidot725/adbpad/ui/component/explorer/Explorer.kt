package jp.kaleidot725.adbpad.ui.component.explorer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.explorer.ExplorerData
import jp.kaleidot725.adbpad.domain.model.explorer.ExplorerDirectory
import jp.kaleidot725.adbpad.domain.model.explorer.ExplorerFile
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground

@Composable
fun Explorer(
    rootDirectory: ExplorerDirectory,
    selectedFile: ExplorerFile,
    expandedDirs: List<ExplorerDirectory>,
    onClickArrow: (ExplorerDirectory) -> Unit,
    onClickFile: (ExplorerFile) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        rootDirectory.list.forEach { file ->
            when (file) {
                is ExplorerData -> {
                    DataFileItem(
                        dataFile = file,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                                .clickableBackground(
                                    isSelected = selectedFile == file,
                                    shape = RoundedCornerShape(12.dp),
                                ).clip(shape = RoundedCornerShape(12.dp))
                                .clickable { onClickFile.invoke(file) }
                                .padding(8.dp),
                    )
                }

                is ExplorerDirectory -> {
                    DirectoryTree(
                        directory = file,
                        selectedFile = selectedFile,
                        expandedDirs = expandedDirs,
                        level = 0,
                        onClickFile = onClickFile,
                        onClickArrow = onClickArrow,
                    )
                }
            }
        }
    }
}
