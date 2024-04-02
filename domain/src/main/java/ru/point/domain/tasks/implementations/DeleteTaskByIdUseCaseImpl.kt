package ru.point.domain.tasks.implementations

import ru.point.data.data.repository_interfaces.TasksRepository
import ru.point.domain.tasks.interfaces.DeleteTaskByIdUseCase
import javax.inject.Inject

class DeleteTaskByIdUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
): DeleteTaskByIdUseCase {
    override fun invoke(id: Long) {
        repository.deleteTaskById(id = id)
    }
}