package com.example.appcash.domain.notes.repository_implementations

import com.example.appcash.data.Folder
import com.example.appcash.data.databases.FoldersDatabase
import com.example.appcash.domain.notes.repository_interfaces.FoldersRepository
import kotlinx.coroutines.flow.Flow

class FoldersRepositoryImpl(
    private val database: FoldersDatabase
) : FoldersRepository {
    override fun getFolders(): Flow<Folder> {
        return database.getFoldersDao().getFolders()
    }
}