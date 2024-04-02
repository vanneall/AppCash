package ru.point.domain.notes.implementations

import ru.point.data.data.repository_interfaces.CategoriesRepository
import ru.point.domain.notes.interfaces.UpdateFolderUseCase
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