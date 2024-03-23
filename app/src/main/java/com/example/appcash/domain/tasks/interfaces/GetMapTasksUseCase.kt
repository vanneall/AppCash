package com.example.appcash.domain.tasks.interfaces

import com.example.appcash.data.entities.TaskWithTask
import com.example.appcash.utils.events.Event
import kotlinx.coroutines.flow.Flow

interface GetMapTasksUseCase {
    fun invoke(folderId: Long? = null, onError: (Event.ErrorEvent) -> Unit): Flow<List<TaskWithTask>>
}