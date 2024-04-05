package ru.point.domain.tasks.interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entities.TaskWithTask

interface GetBookmarksTasksUseCase {
    fun invoke(): Flow<List<TaskWithTask>>
}