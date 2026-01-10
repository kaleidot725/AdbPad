package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.domain.model.command.CommandExecutionHistory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NormalCommandOutputRepositoryImpl : NormalCommandOutputRepository {
    private val _executionHistory = MutableStateFlow<List<CommandExecutionHistory>>(emptyList())
    override val executionHistory: StateFlow<List<CommandExecutionHistory>> = _executionHistory.asStateFlow()

    override suspend fun addExecutionHistory(history: CommandExecutionHistory) {
        val currentHistory = _executionHistory.value.toMutableList()
        currentHistory.add(history)
        _executionHistory.value = currentHistory
    }

    override fun clear() {
        _executionHistory.value = emptyList()
    }
}
