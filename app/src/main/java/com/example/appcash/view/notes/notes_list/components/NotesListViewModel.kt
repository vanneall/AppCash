package com.example.appcash.view.notes.notes_list.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.domain.notes.implementations.GetFolderNameByIdUseCaseImpl
import com.example.appcash.domain.notes.implementations.GetNotesUseCaseImpl
import com.example.appcash.view.Event
import com.example.appcash.view.EventHandler
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


class NotesListViewModel @AssistedInject constructor(
    getNotesUseCase: GetNotesUseCaseImpl,
    getFolderNameByIdUseCase: GetFolderNameByIdUseCaseImpl,
    @Assisted
    private val folderId: Long
) : ViewModel(), EventHandler {

    private val _notes = getNotesUseCase.invoke(id = folderId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _folderName = getFolderNameByIdUseCase.invoke(id = folderId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")

    private val _state = MutableStateFlow(NotesListState(folderId = folderId))

    private val _searchQuery = MutableStateFlow("")

    val state = combine(
        _state,
        _folderName,
        _notes,
        _searchQuery
    ) { state, folderName, notes, searchQuery ->
        state.copy(
            folderName = folderName,
            notes = notes.filter { it.title.contains(searchQuery) }
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), NotesListState())

    override fun handle(event: Event) {
        when (event) {
            is NoteListEvent.SearchNoteEvent -> {
                _searchQuery.update { event.searchQuery }
            }
        }
    }
}




