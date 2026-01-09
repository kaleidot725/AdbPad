package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.domain.model.command.CommandExecutionHistory
import kotlinx.coroutines.flow.Flow

interface NormalCommandOutputRepository {
    fun getExecutionHistoryFlow(): Flow<List<CommandExecutionHistory>>

    suspend fun addExecutionHistory(history: CommandExecutionHistory)

    fun clear()
}
