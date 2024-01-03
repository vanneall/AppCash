package com.example.appcash.view.notes.notes_folders.components

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.data.Folder
import com.example.appcash.domain.notes.interfaces.GetFoldersUseCase
import com.example.appcash.domain.notes.interfaces.InsertFolderUseCase
import com.example.appcash.view.Event
import com.example.appcash.view.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoldersListViewModel @Inject constructor(
    getFoldersUseCase: GetFoldersUseCase,
    private val insertFolderUseCase: InsertFolderUseCase,
) : ViewModel(), EventHandler {

    private val _state = getFoldersUseCase.invoke()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = _state.map { list ->
        FolderListState(folders = list)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), FolderListState())

    override fun handle(event: Event) {
        when (event) {
            is FolderListEvent.CreateFolderEvent -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    if (event.name != "") {
                        insertFolderUseCase.invoke(
                            Folder(
                                name = event.name,
                                color = 1
                            )
                        )
                    }
                }
            }
        }
    }
}