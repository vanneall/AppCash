package ru.point.data.data.datasource.repository.implementations.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.withContext
import ru.point.data.data.datasource.remote.api.FolderApi
import ru.point.data.data.datasource.repository.interfaces.CategoriesRepository
import ru.point.data.data.entity.entities.Category
import javax.inject.Inject

class RemoteCategoriesRepository @Inject constructor(
    private val api: FolderApi
) : CategoriesRepository {

    override suspend fun getCategoryByType(type: Category.Discriminator): Flow<List<Category>> {
        return channelFlow {
            withContext(Dispatchers.IO) {
                send(api.getFolders(userId = 2, folderType = type.name))
            }
        }
    }

    override suspend fun insertCategory(category: Category) {
        withContext(Dispatchers.IO) {
            api.createFolder(category)
        }
    }

    override suspend fun getCategoryNameById(id: Long): Flow<String> {
        return channelFlow {
            withContext(Dispatchers.IO) {
                send((api.getFolders(userId = 2)
                    .find { category -> category.id == id }
                    ?.name ?: ""
                        ))
            }
        }
    }

    override suspend fun deleteCategoryById(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCategory(id: Long, name: String, colorIndex: Int) {
        TODO("Not yet implemented")
    }
}