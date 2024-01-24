package com.example.appcash.domain.notes.implementations

import android.util.Log
import com.example.appcash.data.entities.Folder
import com.example.appcash.data.entities.FolderType
import com.example.appcash.data.repository_interfaces.FoldersRepository
import com.example.appcash.domain.notes.interfaces.GetFoldersByTypeUseCase
import com.example.appcash.utils.events.Event.ErrorEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetFolderByTypeUseCaseImpl @Inject constructor(
    private val repository: FoldersRepository
) : GetFoldersByTypeUseCase {
    override fun invoke(type: FolderType, onError: (ErrorEvent) -> Unit): Flow<List<Folder>> {
        return try {
            repository.getFoldersByType(type = type)
        } catch (ex: Exception) {
            onError(
                ErrorEvent(message = "Selection exception")
            )

            Log.e("Selection exception", ex.stackTrace.contentDeepToString())
            flowOf()
        }
    }
}