package jp.kaleidot725.adbpad.view.common.resource

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.selectedBackground(isSelected: Boolean): Modifier {
    return if (isSelected) {
        this.background(color = MaterialTheme.colors.primary.copy(alpha = 0.2f))
    } else {
        this
    }
}

@Composable
fun Modifier.defaultBorder(): Modifier {
    return this.border(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colors.primary, width = 1.dp)
}
