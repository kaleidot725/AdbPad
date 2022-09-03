package jp.kaleidot725.adbpad.model.usecase

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.request.device.AsyncDeviceMonitorRequest
import com.malinskiy.adam.request.device.Device
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class GetDevicesFlowUseCase {
    operator fun invoke(coroutineScope: CoroutineScope): Flow<List<Device>> {
        val adbClient = AndroidDebugBridgeClientFactory().build()
        val receiveChannel = adbClient.execute(
            request = AsyncDeviceMonitorRequest(),
            scope = coroutineScope
        )
        return receiveChannel.receiveAsFlow()
    }
}
