package jp.kaleidot725.adbpad.ui.screen.text.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.sort.SortType
import jp.kaleidot725.adbpad.ui.component.DefaultTextField
import jp.kaleidot725.adbpad.ui.component.SearchSortDropBox

@Composable
fun TextCommandHeader(
    searchText: String,
    sortType: SortType,
    onUpdateSortType: (SortType) -> Unit,
    onUpdateSearchText: (String) -> Unit,
    onAddNewTextCommand: () -> Unit,
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
            onClick = onAddNewTextCommand,
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    TextCommandHeader(
        searchText = "TEST",
        sortType = SortType.SORT_BY_NAME_ASC,
        onUpdateSortType = {},
        onUpdateSearchText = {},
        onAddNewTextCommand = {},
    )
}
