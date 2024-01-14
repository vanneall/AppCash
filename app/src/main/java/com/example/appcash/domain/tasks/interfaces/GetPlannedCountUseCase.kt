package com.example.appcash.domain.tasks.interfaces

import kotlinx.coroutines.flow.Flow

interface GetPlannedCountUseCase {

    fun invoke(): Flow<Int>

}