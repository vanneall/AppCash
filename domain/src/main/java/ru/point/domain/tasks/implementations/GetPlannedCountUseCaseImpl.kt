package ru.point.domain.tasks.implementations

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.repository_interfaces.TasksRepository
import ru.point.domain.tasks.interfaces.GetPlannedCountUseCase
import javax.inject.Inject

class GetPlannedCountUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : GetPlannedCountUseCase {
    override fun invoke(): Flow<Int> {
        return repository.getPlannedCount()
    }
}