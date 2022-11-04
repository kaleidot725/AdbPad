package jp.kaleidot725.adbpad.domain.di

import jp.kaleidot725.adbpad.domain.usecase.adb.StartAdbUseCase
import jp.kaleidot725.adbpad.domain.usecase.command.ExecuteCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.command.GetCommandList
import jp.kaleidot725.adbpad.domain.usecase.device.GetDevicesFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.SelectDeviceUseCase
import jp.kaleidot725.adbpad.domain.usecase.event.GetEventFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.input.AddInputTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.input.DeleteInputTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.input.ExecuteInputTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.input.GetInputTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.menu.GetMenuListUseCase
import jp.kaleidot725.adbpad.domain.usecase.screenshot.TakeScreenshotUseCase
import jp.kaleidot725.adbpad.domain.usecase.screenshot.TakeThemeScreenshotUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        StartAdbUseCase()
    }
    factory {
        ExecuteCommandUseCase(get(), get())
    }
    factory {
        GetCommandList(get())
    }
    factory {
        GetDevicesFlowUseCase(get())
    }
    factory {
        GetSelectedDeviceFlowUseCase(get())
    }
    factory {
        SelectDeviceUseCase(get())
    }
    factory {
        AddInputTextCommandUseCase(get())
    }
    factory {
        DeleteInputTextCommandUseCase(get())
    }
    factory {
        ExecuteInputTextCommandUseCase(get(), get())
    }
    factory {
        GetInputTextCommandUseCase(get())
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
    factory {
        GetEventFlowUseCase(get())
    }
}
