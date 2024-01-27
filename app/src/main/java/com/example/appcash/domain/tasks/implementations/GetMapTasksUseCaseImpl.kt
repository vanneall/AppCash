package com.example.appcash.domain.tasks.implementations

import android.util.Log
import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.entities.SubTask
import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.tasks.interfaces.GetMapTasksUseCase
import com.example.appcash.utils.events.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetMapTasksUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
): GetMapTasksUseCase {
    override fun invoke(onError: (Event.ErrorEvent) -> Unit): Flow<Map<MainTask, List<SubTask>?>> {
        return try {
            repository.getAllTasks()
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)

            Log.e("Selection exception", ex.stackTrace.contentDeepToString())
            flowOf()
        }
    }
}