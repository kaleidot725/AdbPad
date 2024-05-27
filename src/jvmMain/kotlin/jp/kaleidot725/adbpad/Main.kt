import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import jp.kaleidot725.adbpad.MainCategory
import jp.kaleidot725.adbpad.MainStateHolder
import jp.kaleidot725.adbpad.component.NavigationRail
import jp.kaleidot725.adbpad.domain.di.domainModule
import jp.kaleidot725.adbpad.domain.model.Dialog
import jp.kaleidot725.adbpad.domain.model.Menu
import jp.kaleidot725.adbpad.domain.model.UserColor
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.log.Event
import jp.kaleidot725.adbpad.domain.model.setting.WindowSize
import jp.kaleidot725.adbpad.domain.model.setting.getWindowSize
import jp.kaleidot725.adbpad.repository.di.repositoryModule
import jp.kaleidot725.adbpad.view.di.stateHolderModule
import jp.kaleidot725.adbpad.view.screen.CommandScreen
import jp.kaleidot725.adbpad.view.screen.MenuScreen
import jp.kaleidot725.adbpad.view.screen.ScreenLayout
import jp.kaleidot725.adbpad.view.screen.adberror.AdbErrorScreen
import jp.kaleidot725.adbpad.view.screen.screenshot.ScreenshotScreen
import jp.kaleidot725.adbpad.view.screen.setting.SettingScreen
import jp.kaleidot725.adbpad.view.screen.setting.SettingStateHolder
import jp.kaleidot725.adbpad.view.screen.text.TextCommandScreen
import jp.kaleidot725.adbpad.view.screen.version.VersionScreen
import org.jetbrains.jewel.foundation.theme.JewelTheme
import org.jetbrains.jewel.intui.standalone.theme.IntUiTheme
import org.jetbrains.jewel.intui.standalone.theme.darkThemeDefinition
import org.jetbrains.jewel.intui.standalone.theme.lightThemeDefinition
import org.jetbrains.jewel.intui.window.decoratedWindow
import org.jetbrains.jewel.intui.window.styling.dark
import org.jetbrains.jewel.intui.window.styling.light
import org.jetbrains.jewel.ui.ComponentStyling
import org.jetbrains.jewel.window.DecoratedWindow
import org.jetbrains.jewel.window.DecoratedWindowScope
import org.jetbrains.jewel.window.TitleBar
import org.jetbrains.jewel.window.newFullscreenControls
import org.jetbrains.jewel.window.styling.TitleBarStyle
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

        MaterialTheme(colors = if (state.isDark) DarkColors else LightColors) {
            IntUiTheme(
                theme =
                    if (state.isDark) {
                        JewelTheme.darkThemeDefinition()
                    } else {
                        JewelTheme.lightThemeDefinition()
                    },
                styling =
                    if (state.isDark) {
                        ComponentStyling.decoratedWindow(titleBarStyle = TitleBarStyle.dark())
                    } else {
                        ComponentStyling.decoratedWindow(titleBarStyle = TitleBarStyle.light())
                    },
            ) {
                DecoratedWindow(
                    title = Language.windowTitle,
                    icon = painterResource("icon.png"),
                    onCloseRequest = ::exitApplication,
                    state = windowState,
                ) {
                    TitleBarView()
                    App(mainStateHolder)
                }
            }
        }
    }
}

@Composable
fun DecoratedWindowScope.TitleBarView() {
    TitleBar(
        style = TitleBarStyle.dark(),
        modifier = Modifier.newFullscreenControls(),
    ) {
        Text(
            text = title,
            color = Color.White,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun DecoratedWindowScope.App(mainStateHolder: MainStateHolder) {
    val event by mainStateHolder.event.collectAsState(Event.NULL)
    val state by mainStateHolder.state.collectAsState()
    val decoratedWindowScope = this

    DisposableEffect(mainStateHolder) {
        mainStateHolder.setup()
        onDispose {
            mainStateHolder.saveSetting(decoratedWindowScope.getWindowSize())
            mainStateHolder.dispose()
        }
    }

    Crossfade(state.language) {
        Surface {
            ScreenLayout(
                navigationRail = {
                    NavigationRail(
                        category = state.category,
                        onSelectCategory = mainStateHolder::clickCategory,
                        onOpenSetting = mainStateHolder::openSetting,
                    )
                },
                content = {
                    when (state.category) {
                        MainCategory.Device -> {
                            DeviceContent(mainStateHolder, Modifier.fillMaxSize())
                        }
                        MainCategory.Version -> {
                            val versionStateHolder = mainStateHolder.versionStateHolder
                            val versionState by versionStateHolder.state.collectAsState()

                            DisposableEffect(mainStateHolder) {
                                versionStateHolder.setup()
                                onDispose { versionStateHolder.dispose() }
                            }

                            VersionScreen(versionState, versionStateHolder::retry, Modifier.fillMaxSize())
                        }
                    }
                },
                notificationArea = {
                    Box(
                        Modifier.fillMaxWidth().height(25.dp)
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                    ) {
                        Text(
                            text = event.message,
                            color =
                                when (event.level) {
                                    Event.Level.INFO -> MaterialTheme.colors.onSurface
                                    Event.Level.WARN -> Color.Yellow
                                    Event.Level.ERROR -> Color.Red
                                },
                            style = MaterialTheme.typography.caption,
                        )
                    }
                },
                dialog = {
                    when (state.dialog) {
                        Dialog.Setting -> {
                            val settingStateHolder by remember {
                                mutableStateOf(GlobalContext.get().get<SettingStateHolder>())
                            }
                            val settingState by settingStateHolder.state.collectAsState()

                            DisposableEffect(mainStateHolder) {
                                settingStateHolder.setup()
                                onDispose { settingStateHolder.dispose() }
                            }

                            SettingScreen(
                                languages = settingState.languages,
                                selectLanguage = settingState.selectedLanguage,
                                onUpdateLanguage = settingStateHolder::updateLanguage,
                                appearance = settingState.appearance,
                                updateAppearance = settingStateHolder::updateAppearance,
                                adbDirectoryPath = settingState.adbDirectoryPath,
                                onChangeAdbDirectoryPath = settingStateHolder::updateAdbDirectoryPath,
                                isValidAdbDirectoryPath = settingState.isValidAdbDirectoryPath,
                                adbPortNumber = settingState.adbPortNumber,
                                onChangeAdbPortNumber = settingStateHolder::updateAdbPortNumberPath,
                                isValidAdbPortNumber = settingState.isValidAdbPortNumber,
                                onRestartAdb = settingStateHolder::restartAdb,
                                isRestartingAdb = settingState.isRestartingAdb,
                                onSave = {
                                    settingStateHolder.save { mainStateHolder.closeSetting() }
                                },
                                canSave = settingState.canSave,
                                onCancel = { mainStateHolder.closeSetting() },
                            )
                        }

                        Dialog.AdbError -> {
                            AdbErrorScreen(
                                onOpenSetting = { mainStateHolder.openSetting() },
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

@Composable
private fun DeviceContent(
    mainStateHolder: MainStateHolder,
    modifier: Modifier = Modifier,
) {
    val menuStateHolder = mainStateHolder.menuStateHolder
    val menuState by menuStateHolder.state.collectAsState()

    val commandStateHolder = mainStateHolder.commandStateHolder
    val commandState by commandStateHolder.state.collectAsState()

    val inputTextStateHolder = mainStateHolder.textCommandStateHolder
    val inputTextState by inputTextStateHolder.state.collectAsState()

    val screenshotStateHolder = mainStateHolder.screenshotStateHolder
    val screenshotState by screenshotStateHolder.state.collectAsState()

    Row(modifier) {
        Box(Modifier.background(MaterialTheme.colors.background)) {
            MenuScreen(
                devices = menuState.devices,
                selectedDevice = menuState.selectedDevice,
                onSelectDevice = { menuStateHolder.selectDevice(it) },
                menus = menuState.menus,
                selectedMenu = menuState.selectedMenu,
                onSelectMenu = { menuStateHolder.selectMenu(it) },
                modifier =
                    Modifier
                        .width(250.dp)
                        .fillMaxHeight()
                        .padding(horizontal = 12.dp, vertical = 16.dp),
            )
        }

        Spacer(Modifier.width(1.dp).fillMaxHeight().border(BorderStroke(1.dp, UserColor.getSplitterColor())))

        Box(Modifier.background(MaterialTheme.colors.background)) {
            when (menuState.selectedMenu) {
                Menu.Command -> {
                    CommandScreen(
                        commands = commandState.commands,
                        canExecute = commandState.canExecuteCommand,
                        onExecute = { command ->
                            commandStateHolder.executeCommand(command)
                        },
                    )
                }

                Menu.InputText -> {
                    TextCommandScreen(
                        // InputText
                        inputText = inputTextState.userInputText,
                        onTextChange = { text ->
                            inputTextStateHolder.updateInputText(text)
                        },
                        isSendingInputText = inputTextState.isSendingUserInputText,
                        onSendInputText = {
                            inputTextStateHolder.sendInputText()
                        },
                        canSendInputText = inputTextState.canSendInputText,
                        onSaveInputText = {
                            inputTextStateHolder.saveInputText()
                        },
                        canSaveInputText = inputTextState.canSaveInputText,
                        // Commands
                        commands = inputTextState.commands,
                        onSendCommand = { text ->
                            inputTextStateHolder.sendCommand(text)
                        },
                        canSendCommand = inputTextState.canSendCommand,
                        onDeleteCommand = { text ->
                            inputTextStateHolder.deleteInputText(text)
                        },
                    )
                }

                Menu.Screenshot -> {
                    ScreenshotScreen(
                        screenshot = screenshotState.preview,
                        canCapture = screenshotState.canExecute,
                        isCapturing = screenshotState.isCapturing,
                        commands = screenshotState.commands,
                        onCopyScreenshot = {
                            screenshotStateHolder.copyScreenShotToClipboard()
                        },
                        onDeleteScreenshot = {
                            screenshotStateHolder.deleteScreenShotToClipboard()
                        },
                        onTakeScreenshot = { screenshot ->
                            screenshotStateHolder.takeScreenShot(
                                screenshot,
                            )
                        },
                    )
                }

                null -> Unit
            }
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
