package ru.point.domain.category.interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entity.entities.Category

interface GetCategoryByTypeUseCase {
    fun invoke(type: Category.Discriminator): Flow<List<Category>>
}