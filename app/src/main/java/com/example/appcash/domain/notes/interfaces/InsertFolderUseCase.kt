package com.example.appcash.domain.notes.interfaces

import com.example.appcash.data.entities.Folder

interface InsertFolderUseCase {
    fun invoke(folder: Folder)
}