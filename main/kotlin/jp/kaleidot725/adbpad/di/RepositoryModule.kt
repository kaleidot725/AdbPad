package jp.kaleidot725.adbpad.di

import jp.kaleidot725.adbpad.data.repository.DeviceSettingsRepositoryImpl
import jp.kaleidot725.adbpad.data.repository.SettingRepositoryImpl
import jp.kaleidot725.adbpad.data.repository.TextCommandRepositoryImpl
import jp.kaleidot725.adbpad.domain.repository.DeviceControlCommandRepository
import jp.kaleidot725.adbpad.domain.repository.DeviceControlCommandRepositoryImpl
import jp.kaleidot725.adbpad.domain.repository.DeviceRepository
import jp.kaleidot725.adbpad.domain.repository.DeviceRepositoryImpl
import jp.kaleidot725.adbpad.domain.repository.DeviceSettingsRepository
import jp.kaleidot725.adbpad.domain.repository.NormalCommandRepository
import jp.kaleidot725.adbpad.domain.repository.NormalCommandRepositoryImpl
import jp.kaleidot725.adbpad.domain.repository.ScrcpyNewDisplayProcessRepository
import jp.kaleidot725.adbpad.domain.repository.ScrcpyNewDisplayProcessRepositoryImpl
import jp.kaleidot725.adbpad.domain.repository.ScrcpyProcessRepository
import jp.kaleidot725.adbpad.domain.repository.ScrcpyProcessRepositoryImpl
import jp.kaleidot725.adbpad.domain.repository.ScreenshotCommandRepository
import jp.kaleidot725.adbpad.domain.repository.ScreenshotCommandRepositoryImpl
import jp.kaleidot725.adbpad.domain.repository.SettingRepository
import jp.kaleidot725.adbpad.domain.repository.TextCommandRepository
import org.koin.dsl.module
import ui.repository.VersionRepository

val repositoryModule =
    module {
        single<DeviceRepository> {
            DeviceRepositoryImpl(get())
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
        single<ScrcpyProcessRepository> {
            ScrcpyProcessRepositoryImpl()
        }
        single<ScrcpyNewDisplayProcessRepository> {
            ScrcpyNewDisplayProcessRepositoryImpl()
        }
        factory<DeviceSettingsRepository> {
            DeviceSettingsRepositoryImpl()
        }
    }
