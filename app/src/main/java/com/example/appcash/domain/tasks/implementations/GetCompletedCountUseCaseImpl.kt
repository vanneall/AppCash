package com.example.appcash.domain.tasks.implementations

import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.tasks.interfaces.GetCompletedCountUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCompletedCountUseCaseImpl @Inject constructor(
    private val tasksRepository: TasksRepository
) :GetCompletedCountUseCase{
    override fun invoke(): Flow<Int> {
        return tasksRepository.getCompletedCount()
    }
}