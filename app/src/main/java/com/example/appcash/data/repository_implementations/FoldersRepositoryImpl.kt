package com.example.appcash.data.repository_implementations

import com.example.appcash.data.Folder
import com.example.appcash.data.databases.FoldersDatabase
import com.example.appcash.data.repository_interfaces.FoldersRepository
import kotlinx.coroutines.flow.Flow

class FoldersRepositoryImpl(
    private val database: FoldersDatabase
) : FoldersRepository {
    override fun getFolders(): Flow<List<Folder>> {
        return database.getFoldersDao().getFolders()
    }

    override fun insertFolder(folder: Folder) {
        database.getFoldersDao().insertFolder(folder = folder)
    }
}