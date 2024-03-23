package com.example.appcash.data.repository_interfaces

import com.example.appcash.data.entities.Category
import com.example.appcash.data.entities.Category.Discriminator
import kotlinx.coroutines.flow.Flow


interface CategoriesRepository {
    fun getCategoryByType(type: Discriminator): Flow<List<Category>>

    fun insertCategory(category: Category)

    fun getCategoryNameById(id: Long): Flow<String>

    fun deleteCategoryById(id: Long)

    fun updateCategory(id: Long, name: String, colorIndex: Int)

}