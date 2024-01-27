package com.example.appcash.domain.tasks.implementations

import android.util.Log
import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.tasks.interfaces.GetCompletedCountUseCase
import com.example.appcash.utils.events.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class GetCompletedCountUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : GetCompletedCountUseCase {
    override fun invoke(onError: (Event.ErrorEvent) -> Unit): Flow<Int> {
        return try {
            repository.getCompletedCount()
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)

            Log.e("Selection exception", ex.stackTrace.contentDeepToString())
            emptyFlow()
        }
    }
}