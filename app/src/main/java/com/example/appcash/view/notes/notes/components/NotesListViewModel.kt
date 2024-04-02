package com.example.appcash.view.notes.notes.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.utils.ArgsKeys.FOLDER_ID_KEY
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import ru.point.data.data.entities.Note
import ru.point.domain.notes.implementations.GetCategoryNameByIdUseCaseImpl
import ru.point.domain.notes.implementations.GetNotesUseCaseImpl


class NotesListViewModel @AssistedInject constructor(
    @Assisted(FOLDER_ID_KEY)
    private val folderId: Long?,
    private val getFolderNameByIdUseCase: GetCategoryNameByIdUseCaseImpl,
    private val getAllNotesUseCase: GetNotesUseCaseImpl,
) : ViewModel(), EventHandler {

    private val _notes = initializePrivateState()

    private val _folderName = initializePrivateFolderName()

    val state = combine(
        _folderName,
        _notes,
    ) { folderName, notes ->
        NotesListState(
            folderId = folderId,
            folderName = folderName,
            notesList = notes,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), NotesListState())

    override fun handle(event: Event) {

    }


    private fun initializePrivateState(): Flow<List<Note>> {
        return getAllNotesUseCase
            .invoke(folderId = folderId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    }

    private fun initializePrivateFolderName(): Flow<String> {
        return when (folderId) {
            null -> {
                flowOf("Все заметки")
            }

            else -> {
                getFolderNameByIdUseCase(folderId)
            }
        }
    }
}




