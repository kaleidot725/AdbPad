package jp.kaleidot725.adbpad.di

import jp.kaleidot725.adbpad.domain.repository.DeviceControlCommandRepository
import jp.kaleidot725.adbpad.domain.repository.DeviceRepository
import jp.kaleidot725.adbpad.domain.repository.NormalCommandRepository
import jp.kaleidot725.adbpad.domain.repository.ScreenshotCommandRepository
import jp.kaleidot725.adbpad.domain.repository.SettingRepository
import jp.kaleidot725.adbpad.domain.repository.TextCommandRepository
import jp.kaleidot725.adbpad.repository.impl.DeviceControlCommandRepositoryImpl
import jp.kaleidot725.adbpad.repository.impl.DeviceRepositoryImpl
import jp.kaleidot725.adbpad.repository.impl.NormalCommandRepositoryImpl
import jp.kaleidot725.adbpad.repository.impl.ScreenshotCommandRepositoryImpl
import jp.kaleidot725.adbpad.repository.impl.SettingRepositoryImpl
import jp.kaleidot725.adbpad.repository.impl.TextCommandRepositoryImpl
import org.koin.dsl.module
import ui.repository.VersionRepository

val repositoryModule =
    module {
        single<DeviceRepository> {
            DeviceRepositoryImpl()
        }
        single<NormalCommandRepository> {
            NormalCommandRepositoryImpl()
        }
        single<TextCommandRepository> {
            TextCommandRepositoryImpl()
        }
        single<ScreenshotCommandRepository> {
            ScreenshotCommandRepositoryImpl()
        }
        factory<SettingRepository> {
            SettingRepositoryImpl()
        }
        factory<VersionRepository> {
            VersionRepository()
        }
        factory<DeviceControlCommandRepository> {
            DeviceControlCommandRepositoryImpl()
        }
    }
