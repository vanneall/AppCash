package ru.point.domain.category.implementations

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.point.data.data.datasource.repository.interfaces.CategoriesRepository
import ru.point.domain.category.interfaces.UpdateFolderUseCase
import javax.inject.Inject

class UpdateCategoryUseCaseImpl @Inject constructor(
    private val repository: CategoriesRepository
) : UpdateFolderUseCase {

    override fun invoke(id: Long, name: String, colorIndex: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateCategory(
                id = id,
                name = name,
                colorIndex = colorIndex
            )
        }
    }
}