package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.domain.model.WindowSize

interface WindowSizeRepository {
    suspend fun updateWindowSize(size: WindowSize): Boolean
    suspend fun getWindowSize(): WindowSize
}