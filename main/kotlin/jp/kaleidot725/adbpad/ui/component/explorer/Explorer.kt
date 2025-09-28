package jp.kaleidot725.adbpad.ui.component.explorer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun getBackgrouond(selected: Boolean): Color = if (selected) BottomAppBarDefaults.bottomAppBarFabColor else Color.Transparent

@Composable
fun Explorer(
    rootDirectory: Directory,
    selectedFile: File,
    expandedDirs: List<Directory>,
    onClickArrow: (Directory) -> Unit,
    onClickFile: (File) -> Unit,
    onClickBookmark: (DataFile) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        rootDirectory.list.forEach { file ->
            when (file) {
                is DataFile -> {
                    DataFileItem(
                        dataFile = file,
                        openBookmark = { onClickBookmark.invoke(file) },
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                                .background(
                                    color = getBackgrouond((selectedFile == file)),
                                    shape = RoundedCornerShape(12.dp),
                                ).clip(shape = RoundedCornerShape(12.dp))
                                .clickable { onClickFile.invoke(file) }
                                .padding(8.dp),
                    )
                }

                is Directory -> {
                    DirectoryTree(
                        directory = file,
                        selectedFile = selectedFile,
                        expandedDirs = expandedDirs,
                        level = 0,
                        onClickFile = onClickFile,
                        onClickArrow = onClickArrow,
                        onClickBookmark = onClickBookmark,
                    )
                }
            }
        }
    }
}
