package jp.kaleidot725.adbpad.ui.screen.screenshot.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.sort.SortType
import jp.kaleidot725.adbpad.ui.component.DefaultTextField
import jp.kaleidot725.adbpad.ui.component.SearchSortDropBox

@Composable
fun ScreenshotHeader(
    searchText: String,
    sortType: SortType,
    onUpdateSortType: (SortType) -> Unit,
    onUpdateSearchText: (String) -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
        SearchSortDropBox(
            selectedSortType = sortType,
            onSelectType = onUpdateSortType,
        )

        DefaultTextField(
            initialText = searchText,
            onUpdateText = onUpdateSearchText,
            placeHolder = Language.search,
            modifier = Modifier.align(Alignment.CenterVertically).weight(1.0f),
        )

        IconButton(
            onClick = onDelete,
            modifier =
                Modifier
                    .padding(vertical = 4.dp)
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.CenterVertically),
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "delete",
                modifier = Modifier.height(20.dp),
            )
        }

        Spacer(modifier = Modifier.width(4.dp))
    }
}

@Preview
@Composable
private fun Preview() {
    ScreenshotHeader(
        searchText = "TEST",
        sortType = SortType.SORT_BY_NAME_ASC,
        onUpdateSortType = {},
        onUpdateSearchText = {},
        onDelete = {},
    )
}
