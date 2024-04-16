package ru.point.data.data.repository_implementations

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.point.data.data.dao.CategoryDao
import ru.point.data.data.entities.Category
import ru.point.data.data.entities.Category.Discriminator
import ru.point.data.data.repository_interfaces.CategoriesRepository
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoriesRepository {
    override suspend fun getCategoryByType(type: Discriminator): Flow<List<Category>> {
        return withContext(Dispatchers.IO) {
            categoryDao.readByDiscriminator(discriminator = type)
        }
    }

    override suspend fun insertCategory(category: Category) {
        withContext(Dispatchers.IO) {
            categoryDao.create(value = category)
        }
    }

    override suspend fun getCategoryNameById(id: Long): Flow<String> {
        return withContext(Dispatchers.IO) {
            categoryDao.readById(id = id).map { category -> category.name }
        }
    }

    override suspend fun deleteCategoryById(id: Long) {
        withContext(Dispatchers.IO) {
            categoryDao.deleteById(value = id)
        }
    }

    override suspend fun updateCategory(id: Long, name: String, colorIndex: Int) {
        withContext(Dispatchers.IO) {
            categoryDao.updateFolder(
                id = id,
                name = name,
                colorIndex = colorIndex
            )
        }
    }
}