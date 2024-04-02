package ru.point.domain.tasks.implementations

import ru.point.data.data.repository_interfaces.TasksRepository
import ru.point.domain.tasks.interfaces.UpdateTaskUseCase
import javax.inject.Inject

class UpdateTaskUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : UpdateTaskUseCase {
    override fun invoke(
        id: Long,
        isChecked: Boolean,
    ) {
        repository.updateTask(id = id, isChecked = isChecked)
    }
}