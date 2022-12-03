package jp.kaleidot725.adbpad.domain.model.setting

import jp.kaleidot725.adbpad.domain.model.Language

enum class Appearance(val value: String) {
    DARK(Language.DARK),
    LIGHT(Language.LIGHT),
    SYSTEM(Language.SYSTEM)
}
