package com.example.appcash.view.notes.notes.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.utils.ArgsKeys.FOLDER_ID_KEY
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import com.example.appcash.utils.events.SearchEvent
import com.example.appcash.view.general.other.BottomSheetEvent
import com.example.appcash.view.notes.notefolders.components.MainNotesEvent
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
import ru.point.data.data.entities.Note
import ru.point.domain.notes.implementations.DeleteFolderByIdImpl
import ru.point.domain.notes.implementations.GetCategoryNameByIdUseCaseImpl
import ru.point.domain.notes.implementations.GetNotesUseCaseImpl
import ru.point.domain.notes.implementations.UpdateCategoryUseCaseImpl


class NotesListViewModel @AssistedInject constructor(
    @Assisted(FOLDER_ID_KEY)
    private val folderId: Long?,
    private val getFolderNameByIdUseCase: GetCategoryNameByIdUseCaseImpl,
    private val deleteFolderByIdImpl: Lazy<DeleteFolderByIdImpl>,
    private val updateCategoryUseCaseImpl: Lazy<UpdateCategoryUseCaseImpl>,
    private val getAllNotesUseCase: GetNotesUseCaseImpl,
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
            folderId = folderId,
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
                if (folderId != null) deleteFolder(id = folderId)
            }

            is NoteListEvent.ShowEdit -> {
                showEdit()
            }

            is BottomSheetEvent.HideEvent -> {
                hideEdit()
            }

            is MainNotesEvent.InsertFolder -> {
                updateFolder(
                    name = event.name,
                    colorIndex = event.color
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
            if (folderId != null) {
                updateCategoryUseCaseImpl.get().invoke(
                    id = folderId,
                    name = name.takeIf { it.trim().isNotEmpty() } ?: state.value.folderName,
                    colorIndex = colorIndex
                )
            }
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
            )
        }
    }

    private fun initializePrivateState(): Flow<List<Note>> {
        return getAllNotesUseCase
            .invoke(folderId = folderId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    }

    private fun initializePrivateFolderName(): Flow<String> {
//        return when (openMode) {
//            FolderOpenMode.ALL -> {
//                flowOf("Все заметки")
//            }
//
//            FolderOpenMode.DEFINED -> {
//                getFolderNameByIdUseCase.invoke(
//                    id = folderId
//                )
//            }
//
//            else -> {
//                handle(event = Event.ErrorEvent)
//                flowOf()
//            }
//        }
        return flowOf("wewe")
    }
}




