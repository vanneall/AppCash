package com.example.appcash.domain.notes.implementations

import android.util.Log
import com.example.appcash.data.dto.FolderDto
import com.example.appcash.data.entities.Category
import com.example.appcash.data.entities.Category.Discriminator
import com.example.appcash.data.repository_interfaces.CategoriesRepository
import com.example.appcash.domain.notes.interfaces.GetFoldersByTypeUseCase
import com.example.appcash.utils.ParamsStore.colorsList
import com.example.appcash.utils.ParamsStore.getSafety
import com.example.appcash.utils.events.Event.ErrorEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetFolderByTypeUseCaseImpl @Inject constructor(
    private val repository: CategoriesRepository
) : GetFoldersByTypeUseCase {
    override fun invoke(type: Discriminator, onError: (ErrorEvent) -> Unit): Flow<List<Category>> {
        return try {
            repository.getCategoryByType(type = type)
        } catch (ex: Exception) {
            onError(ErrorEvent)

            Log.e("Selection exception", ex.stackTrace.contentDeepToString())
            flowOf()
        }
    }

    private fun List<Category>.toFolderDtoList(): List<FolderDto> {
        return map { folder ->
            FolderDto(
                id = folder.id,
                name = folder.name,
                color = colorsList.getSafety(folder.colorIndex),
            )
        }
    }
}