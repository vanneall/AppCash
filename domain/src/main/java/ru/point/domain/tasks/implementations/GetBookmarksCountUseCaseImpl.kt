package ru.point.domain.tasks.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.point.data.data.repository_interfaces.TasksRepository
import ru.point.domain.tasks.interfaces.GetBookmarksCountUseCase
import javax.inject.Inject

class GetBookmarksCountUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : GetBookmarksCountUseCase {
    override fun invoke(): Flow<Int> {
        return flow {
            repository.getBookmarksCount().collect { count ->
                emit(count)
            }
        }
    }
}