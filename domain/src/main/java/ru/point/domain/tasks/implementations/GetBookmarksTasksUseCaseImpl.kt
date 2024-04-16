package ru.point.domain.tasks.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.point.data.data.entities.TaskWithTask
import ru.point.data.data.repository_interfaces.TasksRepository
import ru.point.domain.tasks.interfaces.GetBookmarksTasksUseCase
import javax.inject.Inject

class GetBookmarksTasksUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : GetBookmarksTasksUseCase {
    override fun invoke(): Flow<List<TaskWithTask>> {
        return flow { repository.getBookmarkTasks() }
    }
}