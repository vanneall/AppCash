package com.example.appcash.domain.notes.implementations

import android.util.Log
import com.example.appcash.data.repository_interfaces.CategoriesRepository
import com.example.appcash.domain.notes.interfaces.DeleteByIdFolderUseCase
import com.example.appcash.utils.events.Event.ErrorEvent
import javax.inject.Inject

class DeleteFolderByIdImpl @Inject constructor(
    private val repository: CategoriesRepository
): DeleteByIdFolderUseCase {
    override fun invoke(id: Long, onError: (ErrorEvent) -> Unit) {
        try {
            repository.deleteCategoryById(id = id)
        } catch (ex: Exception) {
            onError(ErrorEvent)

            Log.e("Delete exception", ex.stackTrace.contentToString())
        }
    }
}