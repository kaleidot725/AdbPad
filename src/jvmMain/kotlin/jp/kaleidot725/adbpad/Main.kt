package jp.kaleidot725.adbpad

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import jp.kaleidot725.adbpad.core.di.domainModule
import jp.kaleidot725.adbpad.core.di.repositoryModule
import jp.kaleidot725.adbpad.core.di.stateHolderModule
import jp.kaleidot725.adbpad.core.mvi.MVIChildContent
import jp.kaleidot725.adbpad.core.mvi.MVIDialogContent
import jp.kaleidot725.adbpad.core.mvi.MVIRootContent
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.setting.WindowSize
import jp.kaleidot725.adbpad.domain.model.setting.getWindowSize
import jp.kaleidot725.adbpad.ui.common.resource.UserColor
import jp.kaleidot725.adbpad.ui.component.rail.NavigationRail
import jp.kaleidot725.adbpad.ui.screen.CommandScreen
import jp.kaleidot725.adbpad.ui.screen.ScreenLayout
import jp.kaleidot725.adbpad.ui.screen.command.CommandStateHolder
import jp.kaleidot725.adbpad.ui.screen.device.DeviceScreen
import jp.kaleidot725.adbpad.ui.screen.device.DeviceStateHolder
import jp.kaleidot725.adbpad.ui.screen.device.state.DeviceSideEffect
import jp.kaleidot725.adbpad.ui.screen.error.AdbErrorScreen
import jp.kaleidot725.adbpad.ui.screen.screenshot.ScreenshotScreen
import jp.kaleidot725.adbpad.ui.screen.screenshot.ScreenshotStateHolder
import jp.kaleidot725.adbpad.ui.screen.setting.SettingScreen
import jp.kaleidot725.adbpad.ui.screen.setting.SettingStateHolder
import jp.kaleidot725.adbpad.ui.screen.setting.state.SettingSideEffect
import jp.kaleidot725.adbpad.ui.screen.text.TextCommandScreen
import jp.kaleidot725.adbpad.ui.screen.text.TextCommandStateHolder
import jp.kaleidot725.adbpad.ui.section.top.TopSection
import jp.kaleidot725.adbpad.ui.section.top.TopStateHolder
import org.jetbrains.compose.reload.DevelopmentEntryPoint
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.rememberSplitPaneState
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

private val LightColors =
    Colors(
        primary = UserColor.Light.PRIMARY,
        primaryVariant = UserColor.Light.PRIMARY_VARIANT,
        secondary = UserColor.Light.SECONDARY,
        secondaryVariant = UserColor.Light.SECONDARY_VARIANT,
        background = UserColor.Light.BACKGROUND,
        surface = UserColor.Light.SURFACE,
        error = UserColor.Light.ERROR,
        onPrimary = UserColor.Light.ON_PRIMARY,
        onSecondary = UserColor.Light.ON_SECONDARY,
        onError = UserColor.Light.ON_ERROR,
        onBackground = UserColor.Light.ON_BACKGROUND,
        onSurface = UserColor.Light.ON_SURFACE,
        isLight = true,
    )

private val DarkColors =
    Colors(
        primary = UserColor.Dark.PRIMARY,
        primaryVariant = UserColor.Dark.PRIMARY_VARIANT,
        secondary = UserColor.Dark.SECONDARY,
        secondaryVariant = UserColor.Dark.SECONDARY_VARIANT,
        background = UserColor.Dark.BACKGROUND,
        surface = UserColor.Dark.SURFACE,
        error = UserColor.Dark.ERROR,
        onPrimary = UserColor.Dark.ON_PRIMARY,
        onSecondary = UserColor.Dark.ON_SECONDARY,
        onError = UserColor.Dark.ON_ERROR,
        onBackground = UserColor.Dark.ON_BACKGROUND,
        onSurface = UserColor.Dark.ON_SURFACE,
        isLight = false,
    )

fun main() {
    startKoin { modules(repositoryModule, domainModule, stateHolderModule) }
    application {
        val mainStateHolder by remember { mutableStateOf(GlobalContext.get().get<MainStateHolder>()) }
        MVIRootContent(mvi = mainStateHolder) { state, onAction ->
            if (state.size == WindowSize.UNKNOWN) return@MVIRootContent
            if (state.isDark == null) return@MVIRootContent

            val windowState by remember(state.size.width, state.size.height) {
                mutableStateOf(WindowState(width = state.size.width.dp, height = state.size.height.dp))
            }

            Window(
                title = Language.windowTitle,
                icon = painterResource("icon.png"),
                onCloseRequest = {
                    onAction(MainAction.Shutdown)
                    exitApplication()
                },
                state = windowState,
                alwaysOnTop = state.isAlwaysOnTop,
            ) {
                MenuBar {
                    Menu(Language.menuWindow) {
                        CheckboxItem(
                            text = Language.menuWindowMaximize,
                            checked = windowState.placement == WindowPlacement.Maximized,
                            onCheckedChange = {
                                windowState.placement = if (windowState.placement == WindowPlacement.Maximized) {
                                    WindowPlacement.Floating
                                } else {
                                    WindowPlacement.Maximized
                                }
                            }
                        )
                        CheckboxItem(
                            text = Language.menuWindowMinimize,
                            checked = windowState.isMinimized,
                            onCheckedChange = {
                                windowState.isMinimized = !windowState.isMinimized
                            }
                        )
                        CheckboxItem(
                            text = Language.menuWindowFullscreen,
                            checked = windowState.placement == WindowPlacement.Fullscreen,
                            onCheckedChange = {
                                windowState.placement = if (windowState.placement == WindowPlacement.Fullscreen) {
                                    WindowPlacement.Floating
                                } else {
                                    WindowPlacement.Fullscreen
                                }
                            }
                        )
                        CheckboxItem(
                            text = Language.menuWindowAlwaysOnTop,
                            checked = state.isAlwaysOnTop,
                            onCheckedChange = {
                                onAction(MainAction.ToggleAlwaysOnTop)
                            }
                        )
                    }
                }
                DisposableEffect(Unit) { onDispose { onAction(MainAction.SaveSetting(this@Window.getWindowSize())) } }
                MaterialTheme(colors = if (state.isDark) DarkColors else LightColors) {
                    DevelopmentEntryPoint {
                        App(
                            state = state,
                            onMainAction = onAction,
                            onMainRefresh = { mainStateHolder.onRefresh() },
                            commandStateHolder = mainStateHolder.commandStateHolder,
                            textCommandStateHolder = mainStateHolder.textCommandStateHolder,
                            screenshotStateHolder = mainStateHolder.screenshotStateHolder,
                            topStateHolder = mainStateHolder.topStateHolder,
                            deviceStateHolder = mainStateHolder.deviceStateHolder,
                            settingStateHolder = mainStateHolder.settingStateHolder,
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
fun App(
    state: MainState,
    onMainAction: (MainAction) -> Unit,
    onMainRefresh: () -> Unit,
    commandStateHolder: CommandStateHolder,
    textCommandStateHolder: TextCommandStateHolder,
    screenshotStateHolder: ScreenshotStateHolder,
    topStateHolder: TopStateHolder,
    deviceStateHolder: DeviceStateHolder,
    settingStateHolder: SettingStateHolder,
) {
    val textSplitPaneState = rememberSplitPaneState()
    val screenshotSplitPaneState = rememberSplitPaneState()

    Crossfade(state.language) {
        Surface {
            ScreenLayout(
                navigationRail = {
                    NavigationRail(
                        category = state.category,
                        onSelectCategory = { onMainAction(MainAction.ClickCategory(it)) },
                        onOpenSetting = { onMainAction(MainAction.OpenSetting) },
                    )
                },
                top = {
                    MVIChildContent(
                        mvi = topStateHolder,
                        content = { state, onAction ->
                            TopSection(
                                state = state,
                                onAction = onAction,
                                onMainRefresh = onMainRefresh,
                                onMainOpenDevice = { onMainAction(MainAction.OpenDevice) },
                            )
                        },
                    )
                },
                content = {
                    when (state.category) {
                        MainCategory.Command -> {
                            MVIChildContent(
                                mvi = commandStateHolder,
                                content = { state, onAction ->
                                    CommandScreen(
                                        state = state,
                                        onAction = onAction,
                                    )
                                },
                            )
                        }

                        MainCategory.Text -> {
                            MVIChildContent(
                                mvi = textCommandStateHolder,
                                content = { state, onAction ->
                                    TextCommandScreen(
                                        state = state,
                                        onAction = onAction,
                                        splitterState = textSplitPaneState,
                                    )
                                },
                            )
                        }

                        MainCategory.Screenshot -> {
                            MVIChildContent(
                                mvi = screenshotStateHolder,
                                content = { state, onAction ->
                                    ScreenshotScreen(
                                        state = state,
                                        onAction = onAction,
                                        screenshotSplitPaneState = screenshotSplitPaneState,
                                    )
                                },
                            )
                        }

                        MainCategory.File -> {
                            Text("TEST")
                        }
                    }
                },
                dialog = {
                    when (state.dialog) {
                        MainDialog.Setting -> {
                            MVIDialogContent(
                                mvi = settingStateHolder,
                                onSideEffect = {
                                    when (it) {
                                        SettingSideEffect.Saved -> {
                                            onMainRefresh()
                                        }
                                    }
                                },
                            ) { state, onAction ->
                                SettingScreen(
                                    state = state,
                                    onAction = onAction,
                                    onMainRefresh = onMainRefresh,
                                )
                            }
                        }

                        MainDialog.Device -> {
                            MVIDialogContent(
                                mvi = deviceStateHolder,
                                onSideEffect = {
                                    when (it) {
                                        DeviceSideEffect.Close -> onMainRefresh()
                                    }
                                },
                            ) { state, onAction ->
                                DeviceScreen(
                                    state = state,
                                    onAction = onAction,
                                )
                            }
                        }

                        MainDialog.AdbError -> {
                            AdbErrorScreen(
                                onOpenSetting = { onMainAction(MainAction.OpenSetting) },
                            )
                        }

                        MainDialog.Empty -> {
                        }
                    }
                },
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
