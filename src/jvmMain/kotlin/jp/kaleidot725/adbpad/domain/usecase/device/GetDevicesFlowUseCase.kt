package jp.kaleidot725.adbpad.domain.usecase.device

import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.repository.DeviceRepository
import kotlinx.coroutines.flow.Flow

class GetDevicesFlowUseCase(
    private val deviceRepository: DeviceRepository,
) {
    operator fun invoke(): Flow<List<Device>> {
        return deviceRepository.getDeviceFlow()
    }
}
