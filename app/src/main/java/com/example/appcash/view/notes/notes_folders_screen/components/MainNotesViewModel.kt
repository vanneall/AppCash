package com.example.appcash.view.notes.notes_folders_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.data.entities.FolderType
import com.example.appcash.domain.notes.interfaces.GetFoldersByTypeUseCase
import com.example.appcash.domain.notes.interfaces.InsertFolderUseCase
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
class MainNotesViewModel @Inject constructor(
    getFoldersByTypeUseCase: GetFoldersByTypeUseCase,
    private val insertFolderUseCase: InsertFolderUseCase,
) : ViewModel(), EventHandler {

    private val _searchQuery = MutableStateFlow("")

    private val _error = MutableStateFlow<String?>(null)

    private val _mainNotesState = getFoldersByTypeUseCase
        .invoke(
            type = FolderType.NOTES,
            onError = ::handle
        ).map { list ->
            MainNotesState(list = list)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MainNotesState())

    val state = combine(
        _mainNotesState,
        _searchQuery,
        _error
    ) { state, searchQuery, isError ->
        MainNotesState(
            list = state.list.filter { it.name.contains(searchQuery) },
            query = searchQuery,
            error = isError
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MainNotesState())

    override fun handle(event: Event) {
        when (event) {
            is MainNotesEvent.InsertFolderEvent -> {
                insertFolder(name = event.name)
            }

            is SearchEvent -> {
                updateSearchQuery(query = event.query)
            }

            is Event.ErrorEvent -> {
                updateIsError(message = event.message)
            }
        }
    }

    private fun insertFolder(name: String) {
        viewModelScope.launch(context = Dispatchers.IO) {
            insertFolderUseCase.invoke(
                name = name,
                color = 1, //TODO Добавить цвет папкам
                type = FolderType.NOTES,
                onError = ::handle
            )
        }
    }

    private fun updateSearchQuery(query: String) {
        _searchQuery.update { query }
    }

    private fun updateIsError(message: String) {
        _error.update { message }
    }
}