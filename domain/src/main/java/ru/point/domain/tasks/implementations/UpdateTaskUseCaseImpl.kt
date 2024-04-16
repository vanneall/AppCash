package ru.point.domain.tasks.implementations

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.point.data.data.datasource.repository.interfaces.TasksRepository
import ru.point.domain.tasks.interfaces.UpdateTaskUseCase
import java.time.LocalDate
import javax.inject.Inject

class UpdateTaskUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : UpdateTaskUseCase {
    override fun invoke(id: Long, text: String, description: String, date: LocalDate) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateTask(id = id, name = text, description = description, date = date)
        }
    }
}