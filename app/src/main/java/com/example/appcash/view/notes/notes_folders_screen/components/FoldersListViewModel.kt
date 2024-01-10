package com.example.appcash.view.notes.notes_folders_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.data.entities.Folder
import com.example.appcash.domain.notes.interfaces.GetFoldersUseCase
import com.example.appcash.domain.notes.interfaces.InsertFolderUseCase
import com.example.appcash.utils.StringExtensions
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import com.example.appcash.utils.events.SearchEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoldersListViewModel @Inject constructor(
    getFoldersUseCase: GetFoldersUseCase,
    private val insertFolderUseCase: InsertFolderUseCase,
) : ViewModel(), EventHandler {

    private val _searchQuery = MutableStateFlow(StringExtensions.EMPTY_STRING)

    private val _state = getFoldersUseCase.invoke().map { list ->
        FolderListState(folders = list)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), FolderListState())

    val state = combine(_state, _searchQuery) { state, searchQuery ->
        FolderListState(
            folders = state.folders.filter { it.name.contains(searchQuery) }
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), FolderListState())

    override fun handle(event: Event) {
        when (event) {
            is FolderListEvent.CreateFolderEvent -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    insertFolder(name = event.name)
                }
            }
            is SearchEvent -> {
                _searchQuery.update { event.searchQuery }
            }
        }
    }


    private fun insertFolder(name: String) {
        val handledName = name.trim()

        if (handledName.isEmpty()) return

        insertFolderUseCase.invoke(
            Folder(
                name = handledName,
                color = 1 //TODO Добавить цвет папкам
            )
        )
    }
}