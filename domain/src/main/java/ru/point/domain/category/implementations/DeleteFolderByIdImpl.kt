package ru.point.domain.category.implementations

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.point.data.data.datasource.repository.interfaces.CategoriesRepository
import ru.point.domain.notes.interfaces.DeleteByIdFolderUseCase
import javax.inject.Inject

class DeleteFolderByIdImpl @Inject constructor(
    private val repository: CategoriesRepository
) : DeleteByIdFolderUseCase {
    override fun invoke(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteCategoryById(id = id)
        }
    }
}