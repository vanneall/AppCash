package ru.point.data.data.repository_interfaces

import ru.point.data.data.entities.Category
import ru.point.data.data.entities.Category.Discriminator
import kotlinx.coroutines.flow.Flow


interface CategoriesRepository {
    fun getCategoryByType(type: Discriminator): Flow<List<Category>>

    fun insertCategory(category: Category)

    fun getCategoryNameById(id: Long): Flow<String>

    fun deleteCategoryById(id: Long)

    fun updateCategory(id: Long, name: String, colorIndex: Int)

}