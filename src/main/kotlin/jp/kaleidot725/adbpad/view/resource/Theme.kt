package com.example.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import jp.kaleidot725.adbpad.view.resource.md_theme_dark_background
import jp.kaleidot725.adbpad.view.resource.md_theme_dark_error
import jp.kaleidot725.adbpad.view.resource.md_theme_dark_onBackground
import jp.kaleidot725.adbpad.view.resource.md_theme_dark_onError
import jp.kaleidot725.adbpad.view.resource.md_theme_dark_onPrimary
import jp.kaleidot725.adbpad.view.resource.md_theme_dark_onSecondary
import jp.kaleidot725.adbpad.view.resource.md_theme_dark_onSurface
import jp.kaleidot725.adbpad.view.resource.md_theme_dark_primary
import jp.kaleidot725.adbpad.view.resource.md_theme_dark_primaryVariant
import jp.kaleidot725.adbpad.view.resource.md_theme_dark_secondary
import jp.kaleidot725.adbpad.view.resource.md_theme_dark_secondaryVariant
import jp.kaleidot725.adbpad.view.resource.md_theme_dark_surface
import jp.kaleidot725.adbpad.view.resource.md_theme_light_background
import jp.kaleidot725.adbpad.view.resource.md_theme_light_error
import jp.kaleidot725.adbpad.view.resource.md_theme_light_onBackground
import jp.kaleidot725.adbpad.view.resource.md_theme_light_onError
import jp.kaleidot725.adbpad.view.resource.md_theme_light_onSecondary
import jp.kaleidot725.adbpad.view.resource.md_theme_light_onSurface
import jp.kaleidot725.adbpad.view.resource.md_theme_light_primary
import jp.kaleidot725.adbpad.view.resource.md_theme_light_primaryVariant
import jp.kaleidot725.adbpad.view.resource.md_theme_light_secondary
import jp.kaleidot725.adbpad.view.resource.md_theme_light_secondaryVariant
import jp.kaleidot725.adbpad.view.resource.md_theme_light_surface

private val LightColors = Colors(
    primary = md_theme_light_primary,
    primaryVariant = md_theme_light_primaryVariant,
    secondary = md_theme_light_secondary,
    secondaryVariant = md_theme_light_secondaryVariant,
    background = md_theme_light_background,
    surface = md_theme_light_surface,
    error = md_theme_light_error,
    onPrimary = md_theme_dark_onPrimary,
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
