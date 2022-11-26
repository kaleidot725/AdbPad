package jp.kaleidot725.adbpad.view.common.resource

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import jp.kaleidot725.adbpad.domain.model.Color
import jp.kaleidot725.adbpad.domain.model.setting.Appearance

private val LightColors = Colors(
    primary = Color.Light.PRIMARY,
    primaryVariant = Color.Light.PRIMARY_VARIANT,
    secondary = Color.Light.SECONDARY,
    secondaryVariant = Color.Light.SECONDARY_VARIANT,
    background = Color.Light.BACKGROUND,
    surface = Color.Light.SURFACE,
    error = Color.Light.ERROR,
    onPrimary = Color.Light.ON_PRIMARY,
    onSecondary = Color.Light.ON_SECONDARY,
    onError = Color.Light.ON_ERROR,
    onBackground = Color.Light.ON_BACKGROUND,
    onSurface = Color.Light.ON_SURFACE,
    isLight = true
)

private val DarkColors = Colors(
    primary = Color.Dark.PRIMARY,
    primaryVariant = Color.Dark.PRIMARY_VARIANT,
    secondary = Color.Dark.SECONDARY,
    secondaryVariant = Color.Dark.SECONDARY_VARIANT,
    background = Color.Dark.BACKGROUND,
    surface = Color.Dark.SURFACE,
    error = Color.Dark.ERROR,
    onPrimary = Color.Dark.ON_PRIMARY,
    onSecondary = Color.Dark.ON_SECONDARY,
    onError = Color.Dark.ON_ERROR,
    onBackground = Color.Dark.ON_BACKGROUND,
    onSurface = Color.Dark.ON_SURFACE,
    isLight = false
)

@Composable
fun AppTheme(
    appearance: Appearance,
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colors = when (appearance) {
            Appearance.DARK -> DarkColors
            Appearance.LIGHT -> LightColors
            Appearance.SYSTEM -> LightColors
        },
        content = content
    )
}
