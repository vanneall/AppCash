package com.example.appcash.domain.tasks.implementations

import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.tasks.interfaces.GetPlannedCountUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlannedCountUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : GetPlannedCountUseCase {
    override fun invoke(): Flow<Int> {
        return repository.getPlannedCount()
    }
}