package jp.kaleidot725.adbpad.ui.screen.device

import jp.kaleidot725.adbpad.core.mvi.MVIBase
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.device.DeviceSettings
import jp.kaleidot725.adbpad.domain.usecase.device.GetDeviceSettingsUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.SaveDeviceSettingsUseCase
import jp.kaleidot725.adbpad.ui.screen.device.state.DeviceSettingsAction
import jp.kaleidot725.adbpad.ui.screen.device.state.DeviceSettingsSideEffect
import jp.kaleidot725.adbpad.ui.screen.device.state.DeviceSettingsState
import kotlinx.coroutines.launch

class DeviceSettingsStateHolder(
    private val getDeviceSettingsUseCase: GetDeviceSettingsUseCase,
    private val saveDeviceSettingsUseCase: SaveDeviceSettingsUseCase,
) : MVIBase<DeviceSettingsState, DeviceSettingsAction, DeviceSettingsSideEffect>(
    initialUiState = DeviceSettingsState()
) {
    
    override fun onSetup() {
        // Device is initialized externally via initialize() method
    }
    
    override fun onRefresh() {
        // No refresh needed for device settings
    }
    
    fun initialize(device: Device) {
        coroutineScope.launch {
            val deviceSettings = getDeviceSettingsUseCase(device.serial)
            update { 
                copy(
                    device = device,
                    deviceSettings = deviceSettings,
                    isLoaded = true
                )
            }
        }
    }

    override fun onAction(uiAction: DeviceSettingsAction) {
        when (uiAction) {
            is DeviceSettingsAction.UpdateSettings -> updateSettings(uiAction.settings)
            is DeviceSettingsAction.Save -> saveSettings()
            is DeviceSettingsAction.Cancel -> cancel()
        }
    }

    private fun updateSettings(settings: DeviceSettings) {
        update { copy(deviceSettings = settings) }
    }

    private fun saveSettings() {
        val currentState = state.value
        if (currentState.device == null || currentState.deviceSettings == null) return
        
        coroutineScope.launch {
            update { copy(isSaving = true) }
            
            val success = saveDeviceSettingsUseCase(currentState.deviceSettings)
            if (success) {
                sideEffect(DeviceSettingsSideEffect.Saved)
            }
            
            update { copy(isSaving = false) }
        }
    }

    private fun cancel() {
        sideEffect(DeviceSettingsSideEffect.Cancelled)
    }
}