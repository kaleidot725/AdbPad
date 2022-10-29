package jp.kaleidot725.adbpad.view.screen.command

import com.malinskiy.adam.request.device.Device
import jp.kaleidot725.adbpad.domain.model.Command

data class CommandState(
    val commands: List<Command> = emptyList(),
    val selectedDevice: Device? = null,
)
