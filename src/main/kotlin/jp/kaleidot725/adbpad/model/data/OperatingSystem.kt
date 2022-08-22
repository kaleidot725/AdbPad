package jp.kaleidot725.adbpad.model.data

sealed class OperatingSystem(val direcotry: String = "") {
    object Mac : OperatingSystem(System.getProperty("user.home") + "/Library/Application Support/adbpad/")
    object Windows : OperatingSystem(System.getProperty("user.home") + "/adbpad/")
    companion object {
        fun resolveOperationSystem(): OperatingSystem {
            return when (System.getProperty("os.name")) {
                "Mac OS X" -> Mac
                else -> Windows
            }
        }
    }
}
