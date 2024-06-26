package ru.point.domain.category.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.point.data.data.entity.entities.Category
import ru.point.data.data.datasource.repository.interfaces.CategoriesRepository
import ru.point.domain.category.interfaces.GetCategoryByTypeUseCase
import javax.inject.Inject

class GetFolderByTypeUseCaseImpl @Inject constructor(
    private val repository: CategoriesRepository
) : GetCategoryByTypeUseCase {
    override fun invoke(type: Category.Discriminator): Flow<List<Category>> {
        return flow {
            repository.getCategoryByType(type = type).collect { list ->
                emit(list)
            }
        }
    }
}