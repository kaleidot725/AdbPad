package jp.kaleidot725.adbpad.domain.model

enum class DeviceState {
    OFFLINE,
    BOOTLOADER,
    DEVICE,
    HOST,
    RECOVERY,
    RESCUE,
    SIDELOAD,
    UNAUTHORIZED,
    AUTHORIZING,
    CONNECTING,
    UNKNOWN
}
