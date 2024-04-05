package ru.point.domain.tasks.implementations

import ru.point.data.data.entities.Task
import ru.point.data.data.repository_interfaces.TasksRepository
import ru.point.domain.tasks.interfaces.InsertTaskUseCase
import javax.inject.Inject

class InsertTaskUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : InsertTaskUseCase {
    override fun invoke(task: Task) {
        repository.createTask(task)
    }
}