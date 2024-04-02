package ru.point.domain.notes.implementations

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.repository_interfaces.CategoriesRepository
import ru.point.domain.notes.interfaces.GetCategoryNameByIdUseCase
import javax.inject.Inject

class GetCategoryNameByIdUseCaseImpl @Inject constructor(
    private val repository: CategoriesRepository
): GetCategoryNameByIdUseCase {
    override operator fun invoke(id: Long): Flow<String> {
        return repository.getCategoryNameById(id = id)
    }
}