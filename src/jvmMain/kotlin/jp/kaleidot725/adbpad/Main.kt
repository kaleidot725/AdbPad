import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import jp.kaleidot725.adbpad.MainCategory
import jp.kaleidot725.adbpad.MainState
import jp.kaleidot725.adbpad.MainStateHolder
import jp.kaleidot725.adbpad.domain.di.domainModule
import jp.kaleidot725.adbpad.domain.model.Dialog
import jp.kaleidot725.adbpad.domain.model.UserColor
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.setting.WindowSize
import jp.kaleidot725.adbpad.domain.model.setting.getWindowSize
import jp.kaleidot725.adbpad.repository.di.repositoryModule
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground
import jp.kaleidot725.adbpad.ui.component.NavigationRail
import jp.kaleidot725.adbpad.ui.di.stateHolderModule
import jp.kaleidot725.adbpad.ui.screen.CommandScreen
import jp.kaleidot725.adbpad.ui.screen.ScreenLayout
import jp.kaleidot725.adbpad.ui.screen.error.AdbErrorScreen
import jp.kaleidot725.adbpad.ui.screen.menu.component.DropDownDeviceMenu
import jp.kaleidot725.adbpad.ui.screen.screenshot.ScreenshotScreen
import jp.kaleidot725.adbpad.ui.screen.setting.SettingScreen
import jp.kaleidot725.adbpad.ui.screen.setting.SettingStateHolder
import jp.kaleidot725.adbpad.ui.screen.text.TextCommandScreen
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
                    App(mainStateHolder)
                }
            }
        }
    }
}

@Composable
fun WindowScope.App(mainStateHolder: MainStateHolder) {
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
                top = {
                    TitleBarView(
                        state = state,
                        onSelectDevice = mainStateHolder::selectDevice,
                        onRefresh = mainStateHolder::refresh,
                    )
                },
                navigationRail = {
                    NavigationRail(
                        category = state.category,
                        onSelectCategory = mainStateHolder::clickCategory,
                        onOpenSetting = mainStateHolder::openSetting,
                    )
                },
                content = {
                    when (state.category) {
                        MainCategory.Command -> {
                            val commandStateHolder = mainStateHolder.commandStateHolder
                            val commandState by commandStateHolder.state.collectAsState()
                            CommandScreen(
                                commands = commandState.commands,
                                filtered = commandState.filtered,
                                onClickFilter = commandStateHolder::clickTab,
                                canExecute = commandState.canExecuteCommand,
                                onExecute = { command ->
                                    commandStateHolder.executeCommand(command)
                                },
                            )
                        }

                        MainCategory.Text -> {
                            val inputTextStateHolder = mainStateHolder.textCommandStateHolder
                            val inputTextState by inputTextStateHolder.state.collectAsState()

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
                                canSendTabKey = inputTextState.canSendTabKey,
                                onSendTabKey = {
                                    inputTextStateHolder.sendTabCommand()
                                },
                                onSaveInputText = {
                                    inputTextStateHolder.saveInputText()
                                },
                                canSaveInputText = inputTextState.canSaveInputText,
                                // Commands
                                commands = inputTextState.commands,
                                onSendCommand = { text ->
                                    inputTextStateHolder.sendTextCommand(text)
                                },
                                canSendCommand = inputTextState.canSendCommand,
                                isSendingTab = inputTextState.isSendingTab,
                                onDeleteCommand = { text ->
                                    inputTextStateHolder.deleteInputText(text)
                                },
                            )
                        }

                        MainCategory.Screenshot -> {
                            val screenshotStateHolder = mainStateHolder.screenshotStateHolder
                            val screenshotState by screenshotStateHolder.state.collectAsState()

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
                                onSave = { settingStateHolder.save { mainStateHolder.refresh() } },
                                canSave = settingState.canSave,
                                onCancel = { mainStateHolder.refresh() },
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun TitleBarView(
    state: MainState,
    onSelectDevice: (Device) -> Unit,
    onRefresh: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box {
            Row(Modifier.align(Alignment.CenterStart).wrapContentSize()) {
                DropDownDeviceMenu(
                    devices = state.devices,
                    selectedDevice = state.selectedDevice,
                    onSelectDevice = onSelectDevice,
                    modifier = Modifier.wrapContentWidth(),
                )
            }

            Row(Modifier.align(Alignment.CenterEnd).wrapContentSize().padding(4.dp)) {
                var isPress: Boolean by remember { mutableStateOf(false) }
                val degrees: Float by animateFloatAsState(if (isPress) -90f else 0f)
                Box(
                    modifier =
                        Modifier
                            .size(28.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .clickableBackground(isDarker = true)
                            .onPointerEvent(PointerEventType.Press) { isPress = true }
                            .onPointerEvent(PointerEventType.Release) { isPress = false }
                            .clickable { onRefresh() },
                ) {
                    Icon(
                        imageVector = Icons.Default.RestartAlt,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = null,
                        modifier = Modifier.rotate(degrees).align(Alignment.Center),
                    )
                }
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
