package com.example.appcash.domain.tasks.interfaces

import kotlinx.coroutines.flow.Flow

interface GetCompletedCountUseCase {
    fun invoke(): Flow<Int>
}