package com.example.appcash.view.notes.notes_list.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.data.entities.Note
import com.example.appcash.domain.notes.implementations.DeleteFolderByIdImpl
import com.example.appcash.domain.notes.implementations.GetFolderNameByIdUseCaseImpl
import com.example.appcash.domain.notes.implementations.GetNotesByFolderIdUseCaseImpl
import com.example.appcash.domain.notes.implementations.GetNotesUseCaseImpl
import com.example.appcash.domain.notes.implementations.UpdateFolderUseCaseImpl
import com.example.appcash.utils.ArgsKeys.FOLDER_ID_KEY
import com.example.appcash.utils.ArgsKeys.OPEN_MODE_KEY
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import com.example.appcash.utils.events.SearchEvent
import com.example.appcash.view.general.other.BottomSheetEvent
import com.example.appcash.view.notes.notes_folder.components.FolderOpenMode
import com.example.appcash.view.notes.notes_folder.components.MainNotesEvent
import dagger.Lazy
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class NotesListViewModel @AssistedInject constructor(
    @Assisted(FOLDER_ID_KEY)
    private val folderId: Long,
    @Assisted(OPEN_MODE_KEY)
    private val openMode: FolderOpenMode,
    private val getFolderNameByIdUseCase: GetFolderNameByIdUseCaseImpl,
    private val deleteFolderByIdImpl: Lazy<DeleteFolderByIdImpl>,
    private val updateFolderUseCaseImpl: Lazy<UpdateFolderUseCaseImpl>,
    private val getNotesByFolderIdUseCase: Lazy<GetNotesByFolderIdUseCaseImpl>,
    private val getAllNotesUseCase: Lazy<GetNotesUseCaseImpl>,
) : ViewModel(), EventHandler {

    private val _notes = initializePrivateState()

    private val _folderName = initializePrivateFolderName()

    private val _error = MutableStateFlow(false)

    private val _showEdit = MutableStateFlow(false)

    private val _searchQuery = MutableStateFlow("")

    val state = combine(
        _folderName,
        _notes,
        _searchQuery,
        _error,
        _showEdit
    ) { folderName, notes, searchQuery, error, showEdit ->
        NotesListState(
            folderName = folderName,
            notesList = notes.filter { it.title.contains(searchQuery) },
            searchQuery = searchQuery,
            error = error,
            showEdit = showEdit
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), NotesListState())

    override fun handle(event: Event) {
        when (event) {
            is SearchEvent -> {
                updateSearchQuery(query = event.query)
            }

            is Event.ErrorEvent -> {
                updateIsError()
            }

            is NoteListEvent.DeleteFolder -> {
                deleteFolder(id = folderId)
            }

            is NoteListEvent.ShowEdit -> {
                showEdit()
            }

            is BottomSheetEvent.HideEvent -> {
                hideEdit()
            }

            is MainNotesEvent.UpsertFolderEvent -> {
                updateFolder(
                    name = event.name,
                    colorIndex = event.colorIndex
                )
            }
        }
    }

    private fun updateSearchQuery(query: String) {
        _searchQuery.update { query }
    }

    private fun updateIsError() {
        _error.update { true }
    }

    private fun updateFolder(name: String, colorIndex: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            updateFolderUseCaseImpl.get().invoke(
                id = folderId,
                name = name.takeIf { it.trim().isNotEmpty() } ?: state.value.folderName,
                colorIndex = colorIndex,
                onError = ::handle
            )
        }
    }

    private fun showEdit() {
        _showEdit.update { true }
    }

    private fun hideEdit() {
        _showEdit.update { false }
    }

    private fun deleteFolder(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000L) //TODO убрать костыль для удаления элемента
            deleteFolderByIdImpl.get().invoke(
                id = id,
                onError = ::handle
            )
        }
    }

    private fun initializePrivateState(): Flow<List<Note>> {
        return when (openMode) {
            FolderOpenMode.ALL -> {
                getAllNotesUseCase.get().invoke(onError = ::handle)
            }

            FolderOpenMode.DEFINED -> {
                getNotesByFolderIdUseCase.get().invoke(
                    id = folderId,
                    onError = ::handle
                )
            }

            else -> {
                handle(event = Event.ErrorEvent)
                flowOf()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    }

    private fun initializePrivateFolderName(): Flow<String> {
        return when (openMode) {
            FolderOpenMode.ALL -> {
                flowOf("Все заметки")
            }

            FolderOpenMode.DEFINED -> {
                getFolderNameByIdUseCase.invoke(
                    id = folderId,
                    onError = ::handle
                )
            }

            else -> {
                handle(event = Event.ErrorEvent)
                flowOf()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")
    }
}




