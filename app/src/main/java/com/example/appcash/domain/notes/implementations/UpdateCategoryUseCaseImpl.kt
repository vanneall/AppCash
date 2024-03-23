package com.example.appcash.domain.notes.implementations

import com.example.appcash.data.repository_interfaces.CategoriesRepository
import com.example.appcash.domain.notes.interfaces.UpdateFolderUseCase
import javax.inject.Inject

class UpdateCategoryUseCaseImpl @Inject constructor(
    private val repository: CategoriesRepository
) : UpdateFolderUseCase {

    override fun invoke(id: Long, name: String, colorIndex: Int) {
        repository.updateCategory(
            id = id,
            name = name,
            colorIndex = colorIndex
        )
    }
}