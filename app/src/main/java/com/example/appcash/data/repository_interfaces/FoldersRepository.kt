package com.example.appcash.data.repository_interfaces

import com.example.appcash.data.entities.Folder
import com.example.appcash.data.entities.FolderType
import kotlinx.coroutines.flow.Flow


interface FoldersRepository {
    fun getFoldersByType(type: FolderType): Flow<List<Folder>>

    fun insertFolder(folder: Folder)

    fun getFolderNameById(id: Long): Flow<String>

    fun insertFolderWithIcon(folder: Folder, iconId: String)

    fun deleteFolderById(id: Long)

    fun updateFolder(id: Long, name: String, colorIndex: Int)

}