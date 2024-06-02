package jp.kaleidot725.adbpad.ui.di

import jp.kaleidot725.adbpad.MainStateHolder
import jp.kaleidot725.adbpad.ui.screen.menu.command.CommandStateHolder
import jp.kaleidot725.adbpad.ui.screen.menu.MenuStateHolder
import jp.kaleidot725.adbpad.ui.screen.menu.screenshot.ScreenshotStateHolder
import jp.kaleidot725.adbpad.ui.screen.setting.SettingStateHolder
import jp.kaleidot725.adbpad.ui.screen.menu.text.TextCommandStateHolder
import jp.kaleidot725.adbpad.ui.screen.version.VersionStateHolder
import org.koin.dsl.module

val stateHolderModule =
    module {
        factory {
            CommandStateHolder(
                getCommandList = get(),
                executeCommandUseCase = get(),
                getSelectedDeviceFlowUseCase = get(),
            )
        }

        factory {
            TextCommandStateHolder(
                addTextCommandUseCase = get(),
                deleteTextCommandUseCase = get(),
                getTextCommandUseCase = get(),
                executeTextCommandUseCase = get(),
                getSelectedDeviceFlowUseCase = get(),
                sendUserInputTextCommandUseCase = get(),
                sendTabCommandUseCase = get(),
            )
        }

        factory {
            MenuStateHolder(
                getAndroidDevicesFlowUseCase = get(),
                getMenuListUseCase = get(),
                getSelectedDeviceFlowUseCase = get(),
                selectDeviceUseCase = get(),
            )
        }

        factory {
            ScreenshotStateHolder(
                takeScreenshotUseCase = get(),
                getScreenshotCommandUseCase = get(),
                getSelectedDeviceFlowUseCase = get(),
                deleteScreenshotPreviewUseCase = get(),
                copyScreenshotToClipboardUseCase = get(),
            )
        }

        factory {
            SettingStateHolder(
                getSdkPathUseCase = get(),
                saveSdkPathUseCase = get(),
                getAppearanceUseCase = get(),
                saveAppearanceUseCase = get(),
                getLanguageUseCase = get(),
                saveLanguageUseCase = get(),
                restartAdbUseCase = get(),
            )
        }

        factory {
            VersionStateHolder(
                versionRepository = get(),
            )
        }

        factory {
            MainStateHolder(
                menuStateHolder = get(),
                commandStateHolder = get(),
                textCommandStateHolder = get(),
                screenshotStateHolder = get(),
                versionStateHolder = get(),
                getEventFlowUseCase = get(),
                getWindowSizeUseCase = get(),
                saveWindowSizeUseCase = get(),
                startAdbUseCase = get(),
                getDarkModeFlowUseCase = get(),
                getLanguageUseCase = get(),
                refreshUseCase = get(),
            )
        }
    }
