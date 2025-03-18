package jp.kaleidot725.adbpad.ui.screen.screenshot.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    )
}
