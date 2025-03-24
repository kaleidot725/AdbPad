package jp.kaleidot725.adbpad

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import jp.kaleidot725.adbpad.di.domainModule
import jp.kaleidot725.adbpad.di.repositoryModule
import jp.kaleidot725.adbpad.di.stateHolderModule
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.setting.WindowSize
import jp.kaleidot725.adbpad.domain.model.setting.getWindowSize
import jp.kaleidot725.adbpad.ui.common.resource.UserColor
import jp.kaleidot725.adbpad.ui.component.rail.NavigationRail
import jp.kaleidot725.adbpad.ui.screen.CommandScreen
import jp.kaleidot725.adbpad.ui.screen.ScreenLayout
import jp.kaleidot725.adbpad.ui.screen.command.CommandAction
import jp.kaleidot725.adbpad.ui.screen.error.AdbErrorScreen
import jp.kaleidot725.adbpad.ui.screen.screenshot.ScreenshotAction
import jp.kaleidot725.adbpad.ui.screen.screenshot.ScreenshotScreen
import jp.kaleidot725.adbpad.ui.screen.setting.SettingAction
import jp.kaleidot725.adbpad.ui.screen.setting.SettingScreen
import jp.kaleidot725.adbpad.ui.screen.setting.SettingSideEffect
import jp.kaleidot725.adbpad.ui.screen.setting.SettingStateHolder
import jp.kaleidot725.adbpad.ui.screen.text.TextCommandScreen
import jp.kaleidot725.adbpad.ui.section.top.TopAction
import jp.kaleidot725.adbpad.ui.section.top.TopSection
import org.jetbrains.compose.reload.DevelopmentEntryPoint
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.rememberSplitPaneState
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(repositoryModule, domainModule, stateHolderModule)
    }

    application {
        val mainStateHolder by remember { mutableStateOf(GlobalContext.get().get<MainStateHolder>()) }
        val state by mainStateHolder.state.collectAsState()

        if (state.size == WindowSize.UNKNOWN) {
            return@application
        }

        val windowState by remember(state.size.width, state.size.height) {
            mutableStateOf(WindowState(width = state.size.width.dp, height = state.size.height.dp))
        }

        val isDark = state.isDark
        if (isDark != null) {
            MaterialTheme(colors = if (isDark) DarkColors else LightColors) {
                Window(
                    title = Language.windowTitle,
                    icon = painterResource("icon.png"),
                    onCloseRequest = ::exitApplication,
                    state = windowState,
                ) {
                    DevelopmentEntryPoint {
                        App(mainStateHolder)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
fun WindowScope.App(mainStateHolder: MainStateHolder) {
    val state by mainStateHolder.state.collectAsState()
    val decoratedWindowScope = this
    val textSplitPaneState = rememberSplitPaneState()
    val screenshotSplitPaneState = rememberSplitPaneState()

    DisposableEffect(mainStateHolder) {
        mainStateHolder.onSetup()
        onDispose {
            mainStateHolder.onAction(MainAction.SaveSetting(decoratedWindowScope.getWindowSize()))
            mainStateHolder.onDispose()
        }
    }

    Crossfade(state.language) {
        Surface {
            ScreenLayout(
                navigationRail = {
                    NavigationRail(
                        category = state.category,
                        onSelectCategory = mainStateHolder::clickCategory,
                        onOpenSetting = { mainStateHolder.onAction(MainAction.OpenSetting) },
                    )
                },
                top = {
                    val topStateHolder = mainStateHolder.topStateHolder
                    val topState by topStateHolder.state.collectAsState()
                    val onAction = topStateHolder::onAction

                    TopSection(
                        state = topState,
                        onExecuteCommand = { onAction(TopAction.ExecuteCommand(it)) },
                        onSelectDevice = { onAction(TopAction.SelectDevice(it)) },
                        onRefresh = mainStateHolder::onRefresh,
                    )
                },
                content = {
                    when (state.category) {
                        MainCategory.Command -> {
                            val commandStateHolder = mainStateHolder.commandStateHolder
                            val commandState by commandStateHolder.state.collectAsState()
                            val commandAction = commandStateHolder::onAction

                            CommandScreen(
                                commands = commandState.commands,
                                filtered = commandState.filtered,
                                onClickFilter = { commandAction(CommandAction.ClickCategoryTab(it)) },
                                canExecute = commandState.canExecuteCommand,
                                onExecute = { command -> commandAction(CommandAction.ExecuteCommand(command)) },
                            )
                        }

                        MainCategory.Text -> {
                            val inputTextState by mainStateHolder.textCommandStateHolder.state.collectAsState()
                            val onAction = mainStateHolder.textCommandStateHolder::onAction
                            TextCommandScreen(
                                state = inputTextState,
                                onAction = onAction,
                                splitterState = textSplitPaneState,
                            )
                        }

                        MainCategory.Screenshot -> {
                            val screenshotStateHolder = mainStateHolder.screenshotStateHolder
                            val screenshotState by screenshotStateHolder.state.collectAsState()
                            val onAction = screenshotStateHolder::onAction

                            ScreenshotScreen(
                                screenshot = screenshotState.preview,
                                splitterState = screenshotSplitPaneState,
                                screenshots = screenshotState.previews,
                                canCapture = screenshotState.canExecute,
                                isCapturing = screenshotState.isCapturing,
                                selectCommand = screenshotState.selectedCommand,
                                commands = screenshotState.commands,
                                searchText = screenshotState.searchText,
                                sortType = screenshotState.sortType,
                                onOpenDirectory = {
                                    onAction(ScreenshotAction.OpenDirectory)
                                },
                                onCopyScreenshot = {
                                    onAction(ScreenshotAction.CopyScreenshotToClipboard)
                                },
                                onDeleteScreenshot = {
                                    onAction(ScreenshotAction.DeleteScreenshotToClipboard)
                                },
                                onTakeScreenshot = { screenshot ->
                                    onAction(ScreenshotAction.TakeScreenshot(screenshot))
                                },
                                onSelectScreenshot = { screenshot ->
                                    onAction(ScreenshotAction.SelectScreenshot(screenshot))
                                },
                                onNextScreenshot = {
                                    onAction(ScreenshotAction.NextScreenshot)
                                },
                                onPreviousScreenshot = {
                                    onAction(ScreenshotAction.PreviousScreenshot)
                                },
                                onUpdateSearchText = {
                                    onAction(ScreenshotAction.UpdateSearchText(it))
                                },
                                onSelectCommand = {
                                    onAction(ScreenshotAction.SelectScreenshotCommand(it))
                                },
                                onUpdateSortType = {
                                    onAction(ScreenshotAction.UpdateSortType(it))
                                },
                            )
                        }
                    }
                },
                dialog = {
                    when (state.dialog) {
                        MainDialog.Setting -> {
                            val settingStateHolder by remember {
                                mutableStateOf(
                                    GlobalContext.get().get<SettingStateHolder>(),
                                )
                            }
                            val settingState by settingStateHolder.state.collectAsState()
                            val settingAction = settingStateHolder::onAction

                            LaunchedEffect(Unit) {
                                settingStateHolder.sideEffect.collect {
                                    when (it) {
                                        SettingSideEffect.Saved -> mainStateHolder.onRefresh()
                                    }
                                }
                            }

                            DisposableEffect(mainStateHolder) {
                                settingStateHolder.onSetup()
                                onDispose { settingStateHolder.onDispose() }
                            }

                            SettingScreen(
                                initialized = settingState.initialized,
                                languages = settingState.languages,
                                selectLanguage = settingState.selectedLanguage,
                                onUpdateLanguage = { settingAction(SettingAction.UpdateLanguage(it)) },
                                appearance = settingState.appearance,
                                updateAppearance = { settingAction(SettingAction.UpdateAppearance(it)) },
                                adbDirectoryPath = settingState.adbDirectoryPath,
                                onChangeAdbDirectoryPath = { settingAction(SettingAction.UpdateAdbDirectoryPath(it)) },
                                isValidAdbDirectoryPath = settingState.isValidAdbDirectoryPath,
                                adbPortNumber = settingState.adbPortNumber,
                                onChangeAdbPortNumber = { settingAction(SettingAction.UpdateAdbPortNumberPath(it)) },
                                isValidAdbPortNumber = settingState.isValidAdbPortNumber,
                                onSave = { settingAction(SettingAction.Save) },
                                canSave = settingState.canSave,
                                isSaving = settingState.isSaving,
                                onCancel = { mainStateHolder.onRefresh() },
                                canCancel = settingState.canCancel,
                            )
                        }

                        MainDialog.AdbError -> {
                            AdbErrorScreen(
                                onOpenSetting = { mainStateHolder.onAction(MainAction.OpenSetting) },
                            )
                        }

                        null -> Unit
                    }
                },
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

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
