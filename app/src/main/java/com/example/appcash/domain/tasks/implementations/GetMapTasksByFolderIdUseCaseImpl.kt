package com.example.appcash.domain.tasks.implementations

import android.util.Log
import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.entities.SubTask
import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.tasks.interfaces.GetMapTasksByFolderIdUseCase
import com.example.appcash.utils.events.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class GetMapTasksByFolderIdUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : GetMapTasksByFolderIdUseCase {
    override fun invoke(
        id: Long,
        onError: (Event.ErrorEvent) -> Unit
    ): Flow<Map<MainTask, List<SubTask>?>> {
        return try {
            repository.getAllTasksById(id = id)
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)
            Log.e("Insert Subtask exception", ex.stackTraceToString())
            emptyFlow()
        }
    }
}