package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.domain.model.device.Device
import kotlinx.coroutines.flow.Flow

interface DeviceRepository {
    suspend fun selectDevice(device: Device): Boolean
    fun getDeviceFlow(): Flow<List<Device>>
    fun getSelectedDeviceFlow(): Flow<Device?>
}
