package jp.kaleidot725.adbpad.view.common.resource

import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.selectedBackground(isSelected: Boolean): Modifier {
    return if (isSelected) {
        this.background(color = MaterialTheme.colors.primary.copy(alpha = 0.2f))
    } else {
        this
    }
}
