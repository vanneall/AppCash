package com.example.appcash.view.tasks.all_tasks.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.data.entities.Category
import com.example.appcash.data.entities.Category.Discriminator
import com.example.appcash.domain.notes.interfaces.GetCategoryByTypeUseCase
import com.example.appcash.domain.notes.interfaces.InsertFolderUseCase
import com.example.appcash.domain.tasks.interfaces.GetCompletedCountUseCase
import com.example.appcash.domain.tasks.interfaces.GetPlannedCountUseCase
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import com.example.appcash.utils.events.SearchEvent
import com.example.appcash.view.general.other.BottomSheetEvent
import com.example.appcash.view.notes.notes_folder.components.MainNotesEvent
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
class AllTasksFoldersViewModel @Inject constructor(
    getCategoryByTypeUseCase: GetCategoryByTypeUseCase,
    getPlannedCountUseCase: GetPlannedCountUseCase,
    getCompletedCountUseCase: GetCompletedCountUseCase,
    private val insertFolderUseCase: Lazy<InsertFolderUseCase>,
) : ViewModel(), EventHandler {

    private val _searchQuery = MutableStateFlow("")

    private val _isError = MutableStateFlow(false)

    private val _isShowed = MutableStateFlow(false)

    private val _foldersList =
        getCategoryByTypeUseCase.invoke(type = Discriminator.TASKS)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _plannedCount = getPlannedCountUseCase.invoke()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)

    private val _completedCount = getCompletedCountUseCase.invoke()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)

    val state = combine(
        _foldersList,
        _searchQuery,
        _plannedCount,
        _completedCount,
        _isShowed,
        _isError
    ) { args ->
        AllTasksState(
            categories = args[0] as List<Category>,
            searchQuery = args[1] as String,
            plannedTasks = args[2] as Int,
            completeTasks = args[3] as Int,
            isShowed = args[4] as Boolean,
            isError = args[5] as Boolean
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), AllTasksState())

    override fun handle(event: Event) {
        when (event) {
            is MainNotesEvent.UpsertFolderEvent -> {
                insertFolder(
                    name = event.name,
                    colorIndex = event.colorIndex
                )
            }

            is SearchEvent -> {
                updateSearch(query = event.query)
            }

            is BottomSheetEvent.ShowEvent -> {
                updateShow()
            }

            is BottomSheetEvent.HideEvent -> {
                updateHide()
            }

            is Event.ErrorEvent -> {
                updateIsError()
            }
        }
    }


    private fun insertFolder(name: String, colorIndex: Int) {
        viewModelScope.launch(context = Dispatchers.IO) {
            insertFolderUseCase.get().invoke(
                name = name,
                colorIndex = colorIndex,
                discriminator = Discriminator.TASKS,
                iconId = "game_folder_icon",
            )
        }
    }

    private fun updateSearch(query: String) {
        _searchQuery.update { query }
    }

    private fun updateShow() {
        _isShowed.update { true }
    }

    private fun updateHide() {
        _isShowed.update { false }
    }

    private fun updateIsError() {
        _isError.update { true }
    }
}