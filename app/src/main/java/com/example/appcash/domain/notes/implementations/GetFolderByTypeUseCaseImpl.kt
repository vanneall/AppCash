package com.example.appcash.domain.notes.implementations

import android.util.Log
import com.example.appcash.data.dto.FolderDto
import com.example.appcash.data.entities.Folder
import com.example.appcash.data.entities.FolderType
import com.example.appcash.data.repository_interfaces.FoldersRepository
import com.example.appcash.domain.notes.interfaces.GetFoldersByTypeUseCase
import com.example.appcash.utils.ParamsStore.colorsList
import com.example.appcash.utils.ParamsStore.getSafety
import com.example.appcash.utils.events.Event.ErrorEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFolderByTypeUseCaseImpl @Inject constructor(
    private val repository: FoldersRepository
) : GetFoldersByTypeUseCase {
    override fun invoke(type: FolderType, onError: (ErrorEvent) -> Unit): Flow<List<FolderDto>> {
        return try {
            repository.getFoldersByType(type = type).map { list -> list.toFolderDtoList()}
        } catch (ex: Exception) {
            onError(ErrorEvent)

            Log.e("Selection exception", ex.stackTrace.contentDeepToString())
            flowOf()
        }
    }

    private fun List<Folder>.toFolderDtoList(): List<FolderDto> {
        return map { folder ->
            FolderDto(
                id = folder.id,
                name = folder.name,
                color = colorsList.getSafety(folder.colorIndex),
            )
        }
    }
}