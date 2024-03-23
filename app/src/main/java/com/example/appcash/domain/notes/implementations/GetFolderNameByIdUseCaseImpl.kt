package com.example.appcash.domain.notes.implementations

import android.util.Log
import com.example.appcash.data.repository_interfaces.CategoriesRepository
import com.example.appcash.domain.notes.interfaces.GetFolderNameByIdUseCase
import com.example.appcash.utils.events.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetFolderNameByIdUseCaseImpl @Inject constructor(
    private val repository: CategoriesRepository
): GetFolderNameByIdUseCase {
    override fun invoke(id: Long, onError: (Event.ErrorEvent) -> Unit): Flow<String> {
        return try{
            repository.getCategoryNameById(id = id)
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)

            Log.e("Selection exception", ex.stackTrace.contentDeepToString())
            flowOf()
        }
    }
}