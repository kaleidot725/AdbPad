package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.domain.model.device.Device
import kotlinx.coroutines.flow.Flow

interface DeviceRepository {
    fun getSelectedDeviceFlow(): Flow<Device?>

    suspend fun saveDevices(devices: List<Device>): Boolean

    suspend fun selectDevice(device: Device?): Boolean

    suspend fun updateDevices(): List<Device>
}
