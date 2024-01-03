package com.example.appcash.data.repository_interfaces

import com.example.appcash.data.Folder
import kotlinx.coroutines.flow.Flow


interface FoldersRepository {
    fun getFolders(): Flow<List<Folder>>

    fun insertFolder(folder: Folder)
}