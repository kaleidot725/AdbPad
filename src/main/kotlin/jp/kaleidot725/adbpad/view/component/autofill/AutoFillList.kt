package jp.kaleidot725.adbpad.view.component.autofill

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.model.AutoFillText

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoFillList(
    texts: List<AutoFillText>,
    onExecute: (AutoFillText) -> Unit,
    minSize: Dp = 200.dp,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize),
        verticalArrangement = verticalArrangement,
        horizontalArrangement = horizontalArrangement,
        modifier = modifier
    ) {
        items(texts) { text ->
            AutoFillItem(
                text = text,
                onExecute = { onExecute(text) },
                modifier = Modifier.size(minSize)
            )
        }
    }
}
