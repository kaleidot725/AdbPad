package jp.kaleidot725.adbpad.ui.component.dropbox

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.sort.SortType
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground

@Composable
fun SearchSortDropBox(
    selectedSortType: SortType,
    onSelectType: (SortType) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier) {
        Box(
            modifier =
                Modifier
                    .padding(vertical = 4.dp, horizontal = 4.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .clickableBackground(isDarker = !MaterialTheme.colors.isLight)
                    .clickable {
                        expanded = true
                    }.width(40.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier =
                    Modifier
                        .align(Alignment.Center)
                        .offset(x = (-6).dp)
                        .padding(vertical = 8.dp)
                        .size(24.dp),
            )

            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Device DropDown Icon",
                tint = MaterialTheme.colors.onBackground,
                modifier =
                    Modifier
                        .align(Alignment.Center)
                        .offset(x = 10.dp)
                        .size(24.dp),
            )
        }
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
    ) {
        SortType.entries.forEach { sortType ->
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSelectType(sortType)
                },
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Box(
                        modifier =
                            Modifier
                                .size(24.dp)
                                .align(Alignment.CenterVertically),
                    ) {
                        if (selectedSortType == sortType) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Check",
                                tint = MaterialTheme.colors.onBackground,
                                modifier = Modifier.align(Alignment.Center).size(20.dp),
                            )
                        }
                    }

                    Text(
                        text =
                            when (sortType) {
                                SortType.SORT_BY_NAME_ASC -> Language.sortByNameAsc
                                SortType.SORT_BY_NAME_DESC -> Language.sortByNameDesc
                            },
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.align(Alignment.CenterVertically).padding(bottom = 4.dp),
                    )
                }
            }
        }
    }
}
