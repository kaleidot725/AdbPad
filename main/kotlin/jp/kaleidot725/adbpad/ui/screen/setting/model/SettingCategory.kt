package jp.kaleidot725.adbpad.ui.screen.setting.model

import jp.kaleidot725.adbpad.domain.model.language.Language

enum class SettingCategory {
    APPEARANCE,
    SDK,
    ;

    val displayName: String
        get() =
            when (this) {
                APPEARANCE -> Language.categoryAppearance
                SDK -> Language.categorySDK
            }
}
