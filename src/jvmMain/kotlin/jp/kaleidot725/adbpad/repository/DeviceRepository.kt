package jp.kaleidot725.adbpad.repository

import jp.kaleidot725.adbpad.domain.model.Device
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface DeviceRepository {
    fun getDeviceFlow(coroutineScope: CoroutineScope): Flow<List<Device>>
}
