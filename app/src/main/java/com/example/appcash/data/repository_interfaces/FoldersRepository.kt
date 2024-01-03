package com.example.appcash.domain.notes.repository_interfaces

import com.example.appcash.data.Folder
import kotlinx.coroutines.flow.Flow


interface FoldersRepository {
    fun getFolders(): Flow<Folder>
}