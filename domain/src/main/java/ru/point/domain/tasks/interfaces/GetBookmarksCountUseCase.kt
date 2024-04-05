package ru.point.domain.tasks.interfaces

import kotlinx.coroutines.flow.Flow

interface GetBookmarksCountUseCase {
    fun invoke(): Flow<Int>
}