package ru.point.domain.tasks.interfaces

import kotlinx.coroutines.flow.Flow

interface GetAllTasksCountUseCase {
    fun invoke(): Flow<Int>
}