package ru.point.domain.tasks.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.point.data.data.datasource.repository.interfaces.TasksRepository
import ru.point.domain.tasks.interfaces.GetAllTasksCountUseCase
import javax.inject.Inject

class GetAllTasksCountUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : GetAllTasksCountUseCase {
    override fun invoke(): Flow<Int> {
        return flow {
            repository.getAllTasksCount().collect { count ->
                emit(count)
            }
        }
    }
}