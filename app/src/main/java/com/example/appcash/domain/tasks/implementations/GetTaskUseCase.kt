package com.example.appcash.domain.tasks.implementations

import android.util.Log
import com.example.appcash.data.entities.TaskWithTask
import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.tasks.interfaces.GetMapTasksUseCase
import com.example.appcash.utils.events.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    private val repository: TasksRepository
) : GetMapTasksUseCase {
    override fun invoke(
        folderId: Long?,
        onError: (Event.ErrorEvent) -> Unit
    ): Flow<List<TaskWithTask>> {
        return try {
            return if (folderId != null) repository.getTasks(folderId) else repository.getTasks()
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)

            Log.e("Selection exception", ex.stackTrace.contentDeepToString())
            emptyFlow()
        }
    }
}