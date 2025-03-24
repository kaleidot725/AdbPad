package jp.kaleidot725.adbpad.di

import jp.kaleidot725.adbpad.MainStateHolder
import jp.kaleidot725.adbpad.ui.screen.command.CommandStateHolder
import jp.kaleidot725.adbpad.ui.screen.screenshot.ScreenshotStateHolder
import jp.kaleidot725.adbpad.ui.screen.setting.SettingStateHolder
import jp.kaleidot725.adbpad.ui.screen.text.TextCommandStateHolder
import jp.kaleidot725.adbpad.ui.section.top.TopStateHolder
import org.koin.dsl.module

val stateHolderModule =
    module {
        factory {
            CommandStateHolder(
                getNormalCommandGroup = get(),
                executeCommandUseCase = get(),
                getSelectedDeviceFlowUseCase = get(),
            )
        }

        factory {
            TextCommandStateHolder(
                textCommandRepository = get(),
                getTextCommandUseCase = get(),
                executeTextCommandUseCase = get(),
                getSelectedDeviceFlowUseCase = get(),
            )
        }

        factory {
            ScreenshotStateHolder(
                takeScreenshotUseCase = get(),
                getScreenshotCommandUseCase = get(),
                getSelectedDeviceFlowUseCase = get(),
                screenshotCommandRepository = get(),
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
            TopStateHolder(
                updateDevicesUseCase = get(),
                getSelectedDeviceFlowUseCase = get(),
                selectDeviceUseCase = get(),
                executeDeviceControlCommandUseCase = get(),
            )
        }

        factory {
            MainStateHolder(
                commandStateHolder = get(),
                textCommandStateHolder = get(),
                screenshotStateHolder = get(),
                getWindowSizeUseCase = get(),
                saveWindowSizeUseCase = get(),
                startAdbUseCase = get(),
                getDarkModeFlowUseCase = get(),
                getLanguageUseCase = get(),
                refreshUseCase = get(),
                topStateHolder = get(),
            )
        }
    }
