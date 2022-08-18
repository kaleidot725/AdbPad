package jp.kaleidot725.adbpad.view.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.model.AutoFillText
import jp.kaleidot725.adbpad.view.component.autofill.AutoFillList

@Composable
fun AutoFillPane(
    texts: List<AutoFillText>,
    onExecute: (AutoFillText) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        AutoFillList(
            texts = texts,
            onExecute = onExecute,
            minSize = 200.dp,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        )
    }
}
