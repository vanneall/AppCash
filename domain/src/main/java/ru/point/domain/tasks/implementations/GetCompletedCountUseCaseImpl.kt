package ru.point.domain.tasks.implementations

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.repository_interfaces.TasksRepository
import ru.point.domain.tasks.interfaces.GetCompletedCountUseCase
import javax.inject.Inject

class GetCompletedCountUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : GetCompletedCountUseCase {
    override fun invoke(): Flow<Int> {
        return repository.getCompletedCount()
    }
}