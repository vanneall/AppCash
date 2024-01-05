package com.example.appcash.view.notes.notes_folders.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.data.entities.Folder
import com.example.appcash.domain.notes.interfaces.GetFoldersUseCase
import com.example.appcash.domain.notes.interfaces.InsertFolderUseCase
import com.example.appcash.view.Event
import com.example.appcash.view.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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