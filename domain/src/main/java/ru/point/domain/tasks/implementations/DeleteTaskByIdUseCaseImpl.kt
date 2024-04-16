package ru.point.domain.tasks.implementations

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.point.data.data.repository_interfaces.TasksRepository
import ru.point.domain.tasks.interfaces.DeleteTaskByIdUseCase
import javax.inject.Inject

class DeleteTaskByIdUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : DeleteTaskByIdUseCase {
    override fun invoke(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteTaskById(id = id)
        }
    }
}