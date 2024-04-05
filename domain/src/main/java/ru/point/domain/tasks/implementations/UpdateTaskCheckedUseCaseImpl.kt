package ru.point.domain.tasks.implementations

import ru.point.data.data.repository_interfaces.TasksRepository
import ru.point.domain.tasks.interfaces.UpdateTaskCheckedUseCase
import javax.inject.Inject

class UpdateTaskCheckedUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : UpdateTaskCheckedUseCase {
    override fun invoke(
        id: Long,
        isChecked: Boolean,
    ) {
        repository.updateTaskChecked(id = id, isChecked = isChecked)
    }
}