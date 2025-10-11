package jp.kaleidot725.adbpad.domain.usecase.scrcpy

import jp.kaleidot725.adbpad.domain.model.scrcpy.ScrcpyNewDisplayProfile
import jp.kaleidot725.adbpad.domain.model.scrcpy.defaultScrcpyNewDisplayProfiles

class GetScrcpyNewDisplayProfilesUseCase {
    operator fun invoke(): List<ScrcpyNewDisplayProfile> = defaultScrcpyNewDisplayProfiles()
}
