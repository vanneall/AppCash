package ru.point.domain.tasks.implementations

import ru.point.data.data.repository_interfaces.TasksRepository
import ru.point.domain.tasks.interfaces.UpdateTaskUseCase
import java.time.LocalDate
import javax.inject.Inject

class UpdateTaskUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : UpdateTaskUseCase {
    override fun invoke(id: Long, text: String, description: String, date: LocalDate) {
        repository.updateTask(id = id, name = text, description = description, date = date)
    }
}