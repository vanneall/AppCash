package com.example.appcash.domain.notes.implementations

import com.example.appcash.data.entities.Note
import com.example.appcash.data.repository_interfaces.NoteToFolderLinkRepository
import com.example.appcash.domain.notes.interfaces.GetNotesByFolderIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesByFolderIdUseCaseImpl @Inject constructor(
    private val repository: NoteToFolderLinkRepository
): GetNotesByFolderIdUseCase {
    override fun invoke(id: Long): Flow<List<Note>> {
        return repository.getNotesByFolderId(id = id)
    }
}