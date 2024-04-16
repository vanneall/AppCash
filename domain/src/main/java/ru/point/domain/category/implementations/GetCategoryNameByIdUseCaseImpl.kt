package ru.point.domain.category.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.point.data.data.datasource.repository.interfaces.CategoriesRepository
import ru.point.domain.category.interfaces.GetCategoryNameByIdUseCase
import javax.inject.Inject

class GetCategoryNameByIdUseCaseImpl @Inject constructor(
    private val repository: CategoriesRepository
) : GetCategoryNameByIdUseCase {
    override operator fun invoke(id: Long): Flow<String> {
        return flow {
            repository.getCategoryNameById(id = id)
                .collect { name -> emit(name) }
        }
    }
}