package ru.point.domain.tasks.interfaces

import kotlinx.coroutines.flow.Flow

interface GetPlannedCountUseCase {
    fun invoke(): Flow<Int>
}