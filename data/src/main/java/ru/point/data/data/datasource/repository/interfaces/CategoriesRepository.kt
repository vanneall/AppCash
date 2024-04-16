package ru.point.data.data.datasource.repository.interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entity.entities.Category
import ru.point.data.data.entity.entities.Category.Discriminator


interface CategoriesRepository {
    suspend fun getCategoryByType(type: Discriminator): Flow<List<Category>>

    suspend fun insertCategory(category: Category)

    suspend fun getCategoryNameById(id: Long): Flow<String>

    suspend fun deleteCategoryById(id: Long)

    suspend fun updateCategory(id: Long, name: String, colorIndex: Int)

}