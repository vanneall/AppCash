package ru.point.domain.tasks.implementations

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.point.data.data.datasource.repository.interfaces.TasksRepository
import ru.point.domain.tasks.interfaces.UpdateTaskCheckedUseCase
import javax.inject.Inject

class UpdateTaskCheckedUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : UpdateTaskCheckedUseCase {
    override fun invoke(id: Long, isChecked: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateTaskChecked(
                id = id,
                isChecked = isChecked
            )
        }
    }
}