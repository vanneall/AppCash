package com.example.appcash.domain.notes.implementations

import com.example.appcash.data.repository_interfaces.CategoriesRepository
import com.example.appcash.domain.notes.interfaces.DeleteByIdFolderUseCase
import javax.inject.Inject

class DeleteFolderByIdImpl @Inject constructor(
    private val repository: CategoriesRepository
): DeleteByIdFolderUseCase {
    override fun invoke(id: Long) {
        repository.deleteCategoryById(id = id)
    }
}