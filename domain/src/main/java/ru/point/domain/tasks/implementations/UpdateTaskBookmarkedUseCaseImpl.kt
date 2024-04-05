package ru.point.domain.tasks.implementations

import ru.point.data.data.repository_interfaces.TasksRepository
import ru.point.domain.tasks.interfaces.UpdateTasksBookmarkedUseCase
import javax.inject.Inject

class UpdateTaskBookmarkedUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : UpdateTasksBookmarkedUseCase {
    override fun invoke(id: Long) {
        repository.updateBookmarkTasks(id = id)
    }
}