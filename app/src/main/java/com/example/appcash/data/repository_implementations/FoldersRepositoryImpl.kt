package com.example.appcash.data.repository_implementations

import com.example.appcash.data.entities.Folder
import com.example.appcash.data.databases.FoldersDatabase
import com.example.appcash.data.entities.FolderType
import com.example.appcash.data.repository_interfaces.FoldersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoldersRepositoryImpl @Inject constructor(
    private val database: FoldersDatabase
) : FoldersRepository {
    override fun getFolders(type: FolderType): Flow<List<Folder>> {
        return database.getFoldersDao().getFolders(type = type)
    }

    override fun insertFolder(folder: Folder) {
        database.getFoldersDao().insertFolder(folder = folder)
    }

    override fun getFolderNameById(id: Long): Flow<String> {
        return database.getFoldersDao().getFolderNameById(id = id)
    }
}