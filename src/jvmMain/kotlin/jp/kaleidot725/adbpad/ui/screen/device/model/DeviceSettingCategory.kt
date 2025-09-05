package jp.kaleidot725.adbpad.ui.screen.device.model

import jp.kaleidot725.adbpad.domain.model.language.Language

enum class DeviceSettingCategory {
    DEVICE,
    SCRCPY,
    ;

    val displayName: String
        get() =
            when (this) {
                DEVICE -> Language.categoryDevice
                SCRCPY -> Language.categoryScrcpy
            }
}
