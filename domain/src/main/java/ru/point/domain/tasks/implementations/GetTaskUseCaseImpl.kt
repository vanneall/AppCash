package ru.point.domain.tasks.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.point.data.data.entity.entities.TaskWithTask
import ru.point.data.data.datasource.repository.interfaces.TasksRepository
import ru.point.domain.tasks.interfaces.GetTasksUseCase
import javax.inject.Inject

class GetTaskUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : GetTasksUseCase {
    override fun invoke(folderId: Long?): Flow<List<TaskWithTask>> {
        return flow {
            if (folderId != null) repository.getTasks(folderId).collect { list ->
                emit(list)
            } else repository.getTasks().collect { list ->
                emit(list)
            }
        }
    }
}