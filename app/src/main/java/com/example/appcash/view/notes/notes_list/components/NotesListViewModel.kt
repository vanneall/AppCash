package com.example.appcash.view.notes.notes_list.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.data.entities.Note
import com.example.appcash.domain.notes.implementations.GetFolderNameByIdUseCaseImpl
import com.example.appcash.domain.notes.implementations.GetNotesByFolderIdUseCaseImpl
import com.example.appcash.domain.notes.implementations.GetNotesUseCaseImpl
import com.example.appcash.utils.ArgsKeys
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import com.example.appcash.utils.events.SearchEvent
import com.example.appcash.view.notes.notes_folder.components.FolderOpenMode
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


class NotesListViewModel @AssistedInject constructor(
    private val getNoteByFolderIdsUseCase: GetNotesByFolderIdUseCaseImpl,
    private val getFolderNameByIdUseCase: GetFolderNameByIdUseCaseImpl,
    private val getNotesUseCase: GetNotesUseCaseImpl,
    @Assisted(ArgsKeys.OPEN_MODE_KEY)
    private val mode: FolderOpenMode,
    @Assisted(ArgsKeys.FOLDER_ID_KEY)
    private val folderId: Long,
) : ViewModel(), EventHandler {

    private val _notes = initializePrivateState()

    private val _folderName = initializePrivateFolderName()

    private val _state = MutableStateFlow(NotesListState(searchQuery = "", folderId = folderId))

    private val _searchQuery = MutableStateFlow("")

    val state = combine(
        _state,
        _folderName,
        _notes,
        _searchQuery
    ) { state, folderName, notes, searchQuery ->
        state.copy(
            folderName = folderName,
            notes = notes.filter { it.title.contains(searchQuery) },
            searchQuery = searchQuery
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), NotesListState())

    override fun handle(event: Event) {
        when (event) {
            is SearchEvent -> {
                _searchQuery.update { event.query }
            }
        }
    }

    private fun initializePrivateState(): Flow<List<Note>> {
        return when (mode) {
            FolderOpenMode.ALL -> {
                getNotesUseCase.invoke()
            }

            FolderOpenMode.DEFINED -> {
                getNoteByFolderIdsUseCase.invoke(id = folderId)
            }
            else -> flowOf()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    }

    private fun initializePrivateFolderName(): Flow<String> {
        return when (mode) {
            FolderOpenMode.ALL -> {
                flowOf("Все заметки")
            }

            FolderOpenMode.DEFINED -> {
                getFolderNameByIdUseCase.invoke(id = folderId)
            }
            else -> flowOf()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")
    }
}




