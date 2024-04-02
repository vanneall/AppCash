package ru.point.domain.tasks.interfaces

import kotlinx.coroutines.flow.Flow

interface GetCompletedCountUseCase {
    fun invoke(): Flow<Int>
}