package jp.kaleidot725.adbpad.domain.usecase.theme

import jp.kaleidot725.adbpad.domain.model.setting.Appearance
import jp.kaleidot725.adbpad.domain.repository.SettingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class GetDarkModeFlowUseCase(
    private val settingRepository: SettingRepository,
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return when (withContext(Dispatchers.IO) { settingRepository.getAppearance() }) {
            Appearance.DARK -> flow { emit(true) }
            Appearance.LIGHT -> flow { emit(false) }
        }
    }
}
