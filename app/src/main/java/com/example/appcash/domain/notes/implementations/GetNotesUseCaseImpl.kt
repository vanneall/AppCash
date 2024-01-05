package com.example.appcash.domain.notes.implementations

import android.util.Log
import com.example.appcash.data.entities.Note
import com.example.appcash.data.entities.NoteToFolderLink
import com.example.appcash.data.repository_interfaces.NoteToFolderLinkRepository
import com.example.appcash.domain.notes.interfaces.GetNotesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesUseCaseImpl @Inject constructor(
    private val repository: NoteToFolderLinkRepository
): GetNotesUseCase {
    override fun invoke(id: Long): Flow<List<Note>> {
        return repository.getNotesByFolderId(id = id)
    }
}