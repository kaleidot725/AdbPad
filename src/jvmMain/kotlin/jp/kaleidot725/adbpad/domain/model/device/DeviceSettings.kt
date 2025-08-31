package jp.kaleidot725.adbpad.domain.model.device

import kotlinx.serialization.Serializable

@Serializable
data class DeviceSettings(
    val deviceId: String,
    val customName: String? = null,
    val scrcpyOptions: ScrcpyOptions = ScrcpyOptions(),
)

@Serializable
data class ScrcpyOptions(
    // Display options
    val maxSize: Int? = null,
    val bitRate: Int? = null,
    val maxFps: Int? = null,
    val lockVideoOrientation: Int? = null,
    val rotation: Int? = null,
    val stayAwake: Boolean = false,
    val turnScreenOff: Boolean = false,
    val powerOffOnClose: Boolean = false,
    
    // Audio options
    val noAudio: Boolean = false,
    val audioBitRate: Int? = null,
    val audioCodec: String? = null,
    val audioBufferSize: Int? = null,
    val audioOutputBuffer: Int? = null,
    
    // Input options
    val noControl: Boolean = false,
    val disableScreensaver: Boolean = false,
    val shortcutsEnabled: Boolean = true,
    
    // Window options
    val alwaysOnTop: Boolean = false,
    val borderless: Boolean = false,
    val fullscreen: Boolean = false,
    
    // Other options
    val showTouches: Boolean = false,
    val verbosity: Int = 0,
)