package ru.point.domain.tasks.interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entity.entities.TaskWithTask

interface GetTasksUseCase {
    fun invoke(folderId: Long? = null): Flow<List<TaskWithTask>>
}