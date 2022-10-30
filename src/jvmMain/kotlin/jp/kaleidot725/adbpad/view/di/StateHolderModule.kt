package jp.kaleidot725.adbpad.view.di

import jp.kaleidot725.adbpad.MainStateHolder
import jp.kaleidot725.adbpad.view.screen.command.CommandStateHolder
import jp.kaleidot725.adbpad.view.screen.input.InputTextStateHolder
import jp.kaleidot725.adbpad.view.screen.menu.MenuStateHolder
import jp.kaleidot725.adbpad.view.screen.screenshot.ScreenshotStateHolder
import org.koin.dsl.module

val stateHolderModule = module {
    factory {
        CommandStateHolder(
            getNotRunningCommandList = get(),
            getRunningCommandList = get(),
            executeCommandUseCase = get(),
            getSelectedDeviceFlowUseCase = get()
        )
    }

    factory {
        InputTextStateHolder(
            addInputTextUseCase = get(),
            deleteInputTextUseCase = get(),
            getInputTextUseCase = get(),
            executeInputTextCommandUseCase = get()
        )
    }

    factory {
        MenuStateHolder(
            getAndroidDevicesFlowUseCase = get(),
            getMenuListUseCase = get(),
            getSelectedDeviceFlowUseCase = get(),
            selectDeviceUseCase = get()
        )
    }

    factory {
        ScreenshotStateHolder(
            takeScreenshotUseCase = get(),
            takeThemeScreenshotUseCase = get()
        )
    }

    factory {
        MainStateHolder(
            menuStateHolder = get(),
            commandStateHolder = get(),
            inputTextStateHolder = get(),
            screenshotStateHolder = get()
        )
    }
}