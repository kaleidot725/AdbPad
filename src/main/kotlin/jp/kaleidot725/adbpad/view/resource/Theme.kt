package jp.kaleidot725.adbpad.view.resource

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

private val LightColors = Colors(
    primary = ColorRes.PRIMARY,
    primaryVariant = ColorRes.PRIMARY_VARIANT,
    secondary = ColorRes.SECONDARY,
    secondaryVariant = ColorRes.SECONDARY_VARIANT,
    background = ColorRes.BACKGROUND,
    surface = ColorRes.SURFACE,
    error = ColorRes.ERROR,
    onPrimary = ColorRes.ON_PRIMARY,
    onSecondary = ColorRes.ON_SECONDARY,
    onError = ColorRes.ON_ERROR,
    onBackground = ColorRes.ON_BACKGROUND,
    onSurface = ColorRes.ON_SURFACE,
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
