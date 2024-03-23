package com.example.appcash.domain.tasks.interfaces

import com.example.appcash.data.entities.TaskWithTask
import kotlinx.coroutines.flow.Flow

interface GetTasksUseCase {
    fun invoke(folderId: Long? = null): Flow<List<TaskWithTask>>
}