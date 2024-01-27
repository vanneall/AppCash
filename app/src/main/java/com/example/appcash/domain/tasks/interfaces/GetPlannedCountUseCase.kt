package com.example.appcash.domain.tasks.interfaces

import com.example.appcash.utils.events.Event
import kotlinx.coroutines.flow.Flow

interface GetPlannedCountUseCase {
    fun invoke( onError: (Event.ErrorEvent) -> Unit): Flow<Int>
}