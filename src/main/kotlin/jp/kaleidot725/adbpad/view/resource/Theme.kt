package jp.kaleidot725.adbpad.view.resource

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

private val LightColors = Colors(
    primary = md_theme_light_primary,
    primaryVariant = md_theme_light_primaryVariant,
    secondary = md_theme_light_secondary,
    secondaryVariant = md_theme_light_secondaryVariant,
    background = md_theme_light_background,
    surface = md_theme_light_surface,
    error = md_theme_light_error,
    onPrimary = md_theme_light_onPrimary,
    onSecondary = md_theme_light_onSecondary,
    onError = md_theme_light_onError,
    onBackground = md_theme_light_onBackground,
    onSurface = md_theme_light_onSurface,
    isLight = true
)

private val DarkColors = Colors(
    primary = md_theme_dark_primary,
    primaryVariant = md_theme_dark_primaryVariant,
    secondary = md_theme_dark_secondary,
    secondaryVariant = md_theme_dark_secondaryVariant,
    surface = md_theme_dark_surface,
    error = md_theme_dark_error,
    background = md_theme_dark_background,
    onPrimary = md_theme_dark_onPrimary,
    onSecondary = md_theme_dark_onSecondary,
    onError = md_theme_dark_onError,
    onBackground = md_theme_dark_onBackground,
    onSurface = md_theme_dark_onSurface,
    isLight = false
)

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colors = if (!useDarkTheme) LightColors else DarkColors,
        content = content
    )
}
