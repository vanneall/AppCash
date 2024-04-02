package ru.point.domain.notes.implementations

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entities.Category
import ru.point.data.data.repository_interfaces.CategoriesRepository
import ru.point.domain.notes.interfaces.GetCategoryByTypeUseCase
import javax.inject.Inject

class GetFolderByTypeUseCaseImpl @Inject constructor(
    private val repository: CategoriesRepository
) : GetCategoryByTypeUseCase {
    override fun invoke(type: Category.Discriminator): Flow<List<Category>> {
        return repository.getCategoryByType(type = type)
    }
}