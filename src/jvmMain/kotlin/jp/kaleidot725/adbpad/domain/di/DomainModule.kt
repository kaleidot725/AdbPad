package jp.kaleidot725.adbpad.domain.di

import jp.kaleidot725.adbpad.domain.usecase.adb.StartAdbUseCase
import jp.kaleidot725.adbpad.domain.usecase.command.ExecuteCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.command.GetNotRunningCommandList
import jp.kaleidot725.adbpad.domain.usecase.command.GetRunningCommandList
import jp.kaleidot725.adbpad.domain.usecase.device.GetDevicesFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.input.AddInputTextUseCase
import jp.kaleidot725.adbpad.domain.usecase.input.DeleteInputTextUseCase
import jp.kaleidot725.adbpad.domain.usecase.input.ExecuteInputTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.input.GetInputTextUseCase
import jp.kaleidot725.adbpad.domain.usecase.menu.GetMenuListUseCase
import jp.kaleidot725.adbpad.domain.usecase.screenshot.TakeScreenshotUseCase
import jp.kaleidot725.adbpad.domain.usecase.screenshot.TakeThemeScreenshotUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        StartAdbUseCase()
    }
    factory {
        ExecuteCommandUseCase()
    }
    factory {
        GetNotRunningCommandList()
    }
    factory {
        GetRunningCommandList()
    }
    factory {
        GetDevicesFlowUseCase()
    }
    factory {
        AddInputTextUseCase()
    }
    factory {
        DeleteInputTextUseCase()
    }
    factory {
        ExecuteInputTextCommandUseCase()
    }
    factory {
        GetInputTextUseCase()
    }
    factory {
        GetMenuListUseCase()
    }
    factory {
        TakeScreenshotUseCase()
    }
    factory {
        TakeThemeScreenshotUseCase()
    }
}
