package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.domain.model.command.CommandExecutionHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NormalCommandOutputRepositoryImpl : NormalCommandOutputRepository {
    private val executionHistoryFlow = MutableStateFlow<List<CommandExecutionHistory>>(emptyList())

    override fun getExecutionHistoryFlow(): Flow<List<CommandExecutionHistory>> = executionHistoryFlow.asStateFlow()

    override suspend fun addExecutionHistory(history: CommandExecutionHistory) {
        val currentHistory = executionHistoryFlow.value.toMutableList()
        currentHistory.add(history)
        executionHistoryFlow.value = currentHistory
    }

    override fun clear() {
        executionHistoryFlow.value = emptyList()
    }
}
