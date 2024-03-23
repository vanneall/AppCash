package com.example.appcash.view.notes.notes_folder.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.data.entities.Category.Discriminator
import com.example.appcash.domain.notes.interfaces.GetFoldersByTypeUseCase
import com.example.appcash.domain.notes.interfaces.InsertFolderUseCase
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import com.example.appcash.utils.events.SearchEvent
import com.example.appcash.view.general.other.BottomSheetEvent
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainNotesViewModel @Inject constructor(
    getFoldersByTypeUseCase: GetFoldersByTypeUseCase,
    private val insertFolderUseCase: Lazy<InsertFolderUseCase>,
) : ViewModel(), EventHandler {

    private val _searchQuery = MutableStateFlow("")

    private val _error = MutableStateFlow(false)

    private val _isShowed = MutableStateFlow(false)

    private val _foldersDtoList = getFoldersByTypeUseCase
        .invoke(
            type = Discriminator.NOTES,
            onError = ::handle
        ).stateIn(viewModelScope, SharingStarted.WhileSubscribed(), listOf())

    val state = combine(
        _foldersDtoList,
        _searchQuery,
        _error,
        _isShowed
    ) { list, searchQuery, isError, isShowed ->
        MainNotesState(
            foldersList = list.filter { it.name.contains(searchQuery) },
            query = searchQuery,
            error = isError,
            isShow = isShowed
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MainNotesState())

    override fun handle(event: Event) {
        when (event) {
            is MainNotesEvent.UpsertFolderEvent -> {
                insertFolder(
                    name = event.name,
                    colorIndex = event.colorIndex
                )
            }

            is SearchEvent -> {
                updateSearchQuery(query = event.query)
            }

            is Event.ErrorEvent -> {
                updateIsError()
            }

            is BottomSheetEvent.ShowEvent -> {
                showBottomSheet()
            }

            is BottomSheetEvent.HideEvent -> {
                hideBottomSheet()
            }
        }
    }

    private fun insertFolder(name: String, colorIndex: Int) {
        viewModelScope.launch(context = Dispatchers.IO) {
            insertFolderUseCase.get().invoke(
                name = name,
                colorIndex = colorIndex,
                discriminator = Discriminator.NOTES,
                iconId = "technic_folder_icon",
                onError = ::handle
            )
        }
    }

    private fun updateSearchQuery(query: String) {
        _searchQuery.update { query }
    }

    private fun updateIsError() {
        _error.update { true }
    }

    private fun showBottomSheet() {
        _isShowed.update { true }
    }

    private fun hideBottomSheet() {
        _isShowed.update { false }
    }
}