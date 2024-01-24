package com.example.appcash.domain.notes.implementations

import android.util.Log
import com.example.appcash.data.entities.Folder
import com.example.appcash.data.entities.FolderType
import com.example.appcash.data.repository_interfaces.FoldersRepository
import com.example.appcash.domain.notes.interfaces.InsertFolderUseCase
import com.example.appcash.utils.events.Event.ErrorEvent
import javax.inject.Inject

class InsertFolderUseCaseImpl @Inject constructor(
    private val repository: FoldersRepository
) : InsertFolderUseCase {
    override fun invoke(name: String, color: Int, type: FolderType, onError: (ErrorEvent) -> Unit) {
        val nameTrimmed = name.trim()

        if (nameTrimmed.isEmpty()) return

        try {
            repository.insertFolder(
                folder = Folder(
                    name = nameTrimmed,
                    color = color,
                    type = type
                )
            )
        } catch (ex: Exception) {
            onError(
                ErrorEvent(message = "Insert exception")
            )

            Log.e("Insert exception", ex.stackTrace.contentToString())
        }
    }
}