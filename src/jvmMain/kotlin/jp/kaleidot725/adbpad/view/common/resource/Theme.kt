package jp.kaleidot725.adbpad.view.common.resource

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import jp.kaleidot725.adbpad.domain.model.Color

private val LightColors = Colors(
    primary = Color.PRIMARY,
    primaryVariant = Color.PRIMARY_VARIANT,
    secondary = Color.SECONDARY,
    secondaryVariant = Color.SECONDARY_VARIANT,
    background = Color.BACKGROUND,
    surface = Color.SURFACE,
    error = Color.ERROR,
    onPrimary = Color.ON_PRIMARY,
    onSecondary = Color.ON_SECONDARY,
    onError = Color.ON_ERROR,
    onBackground = Color.ON_BACKGROUND,
    onSurface = Color.ON_SURFACE,
    isLight = true
)

@Composable
fun AppTheme(
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colors = LightColors,
        content = content
    )
}
