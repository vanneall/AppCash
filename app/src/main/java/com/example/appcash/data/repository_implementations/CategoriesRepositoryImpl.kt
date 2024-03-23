package com.example.appcash.data.repository_implementations

import com.example.appcash.data.dao.CategoryDao
import com.example.appcash.data.entities.Category
import com.example.appcash.data.entities.Category.Discriminator
import com.example.appcash.data.repository_interfaces.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoriesRepository {
    override fun getCategoryByType(type: Discriminator): Flow<List<Category>> {
        return categoryDao.readByDiscriminator(discriminator = type)
    }

    override fun insertCategory(category: Category) {
        categoryDao.create(value = category)
    }

    override fun getCategoryNameById(id: Long): Flow<String> {
        return categoryDao.readById(id = id).map { folder -> folder.name }
    }

    override fun deleteCategoryById(id: Long) {
        categoryDao.deleteById(value = id)
    }

    override fun updateCategory(id: Long, name: String, colorIndex: Int) {
        categoryDao.updateFolder(
            id = id,
            name = name,
            colorIndex = colorIndex
        )
    }
}