package com.example.appcash.domain.notes.interfaces

import com.example.appcash.data.Folder

interface InsertFolderUseCase {
    fun invoke(folder: Folder)
}