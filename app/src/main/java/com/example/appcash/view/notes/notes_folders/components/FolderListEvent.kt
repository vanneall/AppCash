package com.example.appcash.view.notes.notes_folders.components

sealed class FolderListIntent {
    object CreateFolderIntent : FolderListIntent()
    data class OpenFolderIntent(
        val index: Int
    ) : FolderListIntent()
}

