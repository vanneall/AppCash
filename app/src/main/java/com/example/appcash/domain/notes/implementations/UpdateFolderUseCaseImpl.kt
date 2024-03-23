package com.example.appcash.domain.notes.implementations

import android.util.Log
import com.example.appcash.data.repository_interfaces.CategoriesRepository
import com.example.appcash.domain.notes.interfaces.UpdateFolderUseCase
import com.example.appcash.utils.events.Event.ErrorEvent
import javax.inject.Inject

class UpdateFolderUseCaseImpl @Inject constructor(
    private val repository: CategoriesRepository
) : UpdateFolderUseCase {

    override fun invoke(id: Long, name: String, colorIndex: Int, onError: (ErrorEvent) -> Unit) {
        try {
            repository.updateCategory(
                id = id,
                name = name,
                colorIndex = colorIndex
            )
        } catch (ex: Exception) {
            onError(ErrorEvent)

            Log.e("Update folder exception", ex.stackTrace.contentToString())
        }
    }
}