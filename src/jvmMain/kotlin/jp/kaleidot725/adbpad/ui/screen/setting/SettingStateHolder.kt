package jp.kaleidot725.adbpad.ui.screen.setting

import jp.kaleidot725.adbpad.core.mvi.MVIBase
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.setting.Appearance
import jp.kaleidot725.adbpad.domain.usecase.adb.RestartAdbUseCase
import jp.kaleidot725.adbpad.domain.usecase.appearance.GetAppearanceUseCase
import jp.kaleidot725.adbpad.domain.usecase.appearance.SaveAppearanceUseCase
import jp.kaleidot725.adbpad.domain.usecase.language.GetLanguageUseCase
import jp.kaleidot725.adbpad.domain.usecase.language.SaveLanguageUseCase
import jp.kaleidot725.adbpad.domain.usecase.sdkpath.GetSdkPathUseCase
import jp.kaleidot725.adbpad.domain.usecase.sdkpath.SaveSdkPathUseCase
import jp.kaleidot725.adbpad.ui.screen.setting.state.SettingAction
import jp.kaleidot725.adbpad.ui.screen.setting.state.SettingSideEffect
import jp.kaleidot725.adbpad.ui.screen.setting.state.SettingState
import kotlinx.coroutines.launch

class SettingStateHolder(
    private val getSdkPathUseCase: GetSdkPathUseCase,
    private val saveSdkPathUseCase: SaveSdkPathUseCase,
    private val getAppearanceUseCase: GetAppearanceUseCase,
    private val saveAppearanceUseCase: SaveAppearanceUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val saveLanguageUseCase: SaveLanguageUseCase,
    private val restartAdbUseCase: RestartAdbUseCase,
) : MVIBase<SettingState, SettingAction, SettingSideEffect>(initialUiState = SettingState()) {
    private var oldAdbDirectoryPath: String = ""
    private var oldAdbPortNumber: Int = 0

    override fun onSetup() {
        coroutineScope.launch {
            val language = getLanguageUseCase()
            val appearance = getAppearanceUseCase()
            val sdkPath = getSdkPathUseCase()
            val adbDirectoryPath = sdkPath.adbDirectory
            val adbPortNumber = sdkPath.adbServerPort.toString()

            oldAdbDirectoryPath = sdkPath.adbDirectory
            oldAdbPortNumber = sdkPath.adbServerPort

            update {
                this.copy(
                    selectedLanguage = language,
                    appearance = appearance,
                    adbDirectoryPath = adbDirectoryPath,
                    adbPortNumber = adbPortNumber,
                    initialized = true,
                )
            }
        }
    }

    override fun onAction(uiAction: SettingAction) {
        coroutineScope.launch {
            when (uiAction) {
                SettingAction.Save -> save()
                is SettingAction.UpdateAdbDirectoryPath -> updateAdbDirectoryPath(uiAction.value)
                is SettingAction.UpdateAdbPortNumberPath -> updateAdbPortNumberPath(uiAction.value)
                is SettingAction.UpdateAppearance -> updateAppearance(uiAction.value)
                is SettingAction.UpdateLanguage -> updateLanguage(uiAction.value)
            }
        }
    }

    override fun onRefresh() {}

    override fun onReset() {
        update { SettingState() }
        super.onReset()
    }

    private suspend fun save() {
        update { this.copy(isSaving = true) }
        saveLanguageUseCase(currentState.selectedLanguage)
        saveAppearanceUseCase(currentState.appearance)
        saveSdkPathUseCase(currentState.adbDirectoryPath, currentState.adbPortNumber.toIntOrNull())
        restartAdbUseCase(oldAdbDirectory = oldAdbDirectoryPath, oldServerPort = oldAdbPortNumber)
        sideEffect(SettingSideEffect.Saved)
    }

    private fun updateLanguage(value: Language.Type) {
        update { this.copy(selectedLanguage = value) }
    }

    private fun updateAppearance(value: Appearance) {
        update { this.copy(appearance = value) }
    }

    private fun updateAdbDirectoryPath(value: String) {
        update { this.copy(adbDirectoryPath = value) }
    }

    private fun updateAdbPortNumberPath(value: String) {
        update { this.copy(adbPortNumber = value) }
    }
}
