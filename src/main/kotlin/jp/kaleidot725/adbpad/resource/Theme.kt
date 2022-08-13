package com.example.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import jp.kaleidot725.adbpad.resource.md_theme_dark_background
import jp.kaleidot725.adbpad.resource.md_theme_dark_error
import jp.kaleidot725.adbpad.resource.md_theme_dark_errorContainer
import jp.kaleidot725.adbpad.resource.md_theme_dark_inverseOnSurface
import jp.kaleidot725.adbpad.resource.md_theme_dark_inversePrimary
import jp.kaleidot725.adbpad.resource.md_theme_dark_inverseSurface
import jp.kaleidot725.adbpad.resource.md_theme_dark_onBackground
import jp.kaleidot725.adbpad.resource.md_theme_dark_onError
import jp.kaleidot725.adbpad.resource.md_theme_dark_onErrorContainer
import jp.kaleidot725.adbpad.resource.md_theme_dark_onPrimary
import jp.kaleidot725.adbpad.resource.md_theme_dark_onPrimaryContainer
import jp.kaleidot725.adbpad.resource.md_theme_dark_onSecondary
import jp.kaleidot725.adbpad.resource.md_theme_dark_onSecondaryContainer
import jp.kaleidot725.adbpad.resource.md_theme_dark_onSurface
import jp.kaleidot725.adbpad.resource.md_theme_dark_onSurfaceVariant
import jp.kaleidot725.adbpad.resource.md_theme_dark_onTertiary
import jp.kaleidot725.adbpad.resource.md_theme_dark_onTertiaryContainer
import jp.kaleidot725.adbpad.resource.md_theme_dark_outline
import jp.kaleidot725.adbpad.resource.md_theme_dark_primary
import jp.kaleidot725.adbpad.resource.md_theme_dark_primaryContainer
import jp.kaleidot725.adbpad.resource.md_theme_dark_secondary
import jp.kaleidot725.adbpad.resource.md_theme_dark_secondaryContainer
import jp.kaleidot725.adbpad.resource.md_theme_dark_surface
import jp.kaleidot725.adbpad.resource.md_theme_dark_surfaceVariant
import jp.kaleidot725.adbpad.resource.md_theme_dark_tertiary
import jp.kaleidot725.adbpad.resource.md_theme_dark_tertiaryContainer
import jp.kaleidot725.adbpad.resource.md_theme_light_background
import jp.kaleidot725.adbpad.resource.md_theme_light_error
import jp.kaleidot725.adbpad.resource.md_theme_light_errorContainer
import jp.kaleidot725.adbpad.resource.md_theme_light_inverseOnSurface
import jp.kaleidot725.adbpad.resource.md_theme_light_inversePrimary
import jp.kaleidot725.adbpad.resource.md_theme_light_inverseSurface
import jp.kaleidot725.adbpad.resource.md_theme_light_onBackground
import jp.kaleidot725.adbpad.resource.md_theme_light_onError
import jp.kaleidot725.adbpad.resource.md_theme_light_onErrorContainer
import jp.kaleidot725.adbpad.resource.md_theme_light_onPrimary
import jp.kaleidot725.adbpad.resource.md_theme_light_onPrimaryContainer
import jp.kaleidot725.adbpad.resource.md_theme_light_onSecondary
import jp.kaleidot725.adbpad.resource.md_theme_light_onSecondaryContainer
import jp.kaleidot725.adbpad.resource.md_theme_light_onSurface
import jp.kaleidot725.adbpad.resource.md_theme_light_onSurfaceVariant
import jp.kaleidot725.adbpad.resource.md_theme_light_onTertiary
import jp.kaleidot725.adbpad.resource.md_theme_light_onTertiaryContainer
import jp.kaleidot725.adbpad.resource.md_theme_light_outline
import jp.kaleidot725.adbpad.resource.md_theme_light_primary
import jp.kaleidot725.adbpad.resource.md_theme_light_primaryContainer
import jp.kaleidot725.adbpad.resource.md_theme_light_secondary
import jp.kaleidot725.adbpad.resource.md_theme_light_secondaryContainer
import jp.kaleidot725.adbpad.resource.md_theme_light_surface
import jp.kaleidot725.adbpad.resource.md_theme_light_surfaceVariant
import jp.kaleidot725.adbpad.resource.md_theme_light_tertiary
import jp.kaleidot725.adbpad.resource.md_theme_light_tertiaryContainer

private val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    onError = md_theme_light_onError,
    errorContainer = md_theme_light_errorContainer,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseSurface = md_theme_light_inverseSurface,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inversePrimary = md_theme_light_inversePrimary,
)

private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    onError = md_theme_dark_onError,
    errorContainer = md_theme_dark_errorContainer,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseSurface = md_theme_dark_inverseSurface,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inversePrimary = md_theme_dark_inversePrimary,
)

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}