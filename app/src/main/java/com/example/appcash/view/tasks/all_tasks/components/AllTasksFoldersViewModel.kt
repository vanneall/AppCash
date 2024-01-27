package com.example.appcash.view.tasks.all_tasks.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.data.entities.FolderType
import com.example.appcash.domain.notes.interfaces.GetFoldersByTypeUseCase
import com.example.appcash.domain.notes.interfaces.InsertFolderUseCase
import com.example.appcash.domain.tasks.implementations.GetPlannedCountUseCaseImpl
import com.example.appcash.domain.tasks.interfaces.GetCompletedCountUseCase
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import com.example.appcash.utils.events.SearchEvent
import com.example.appcash.view.notes.notes_folder.components.MainNotesEvent
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
class AllTasksFoldersViewModel @Inject constructor(
    getFoldersByTypeUseCase: GetFoldersByTypeUseCase,
    getPlannedCountUseCaseImpl: GetPlannedCountUseCaseImpl,
    getCompletedCountUseCase: GetCompletedCountUseCase,
    private val insertFolderUseCase: InsertFolderUseCase,
) : ViewModel(), EventHandler {
    private val _searchQuery = MutableStateFlow("")

    private val _state = getFoldersByTypeUseCase.invoke(type = FolderType.TASKS, {}).map { list ->
        AllTasksState(folders = list)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), AllTasksState())

    private val _plannedCount = getPlannedCountUseCaseImpl.invoke()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)

    private val _completedCount = getCompletedCountUseCase.invoke()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)

    val state = combine(
        _state,
        _searchQuery,
        _plannedCount,
        _completedCount
    ) { state, searchQuery, plannedCount, completedCount ->
        AllTasksState(
            folders = state.folders.filter { it.name.contains(searchQuery) },
            searchQuery = searchQuery,
            plannedTasks = plannedCount,
            completeTasks = completedCount
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), AllTasksState())

    override fun handle(event: Event) {
        when (event) {
            is MainNotesEvent.UpsertFolderEvent -> {
                insertFolder(
                    name = event.name
                )
            }

            is SearchEvent -> {
                _searchQuery.update { event.query }
            }
        }
    }


    private fun insertFolder(name: String) {
        viewModelScope.launch(context = Dispatchers.IO) {
            insertFolderUseCase.invoke(
                name = name,
                colorIndex = 0, //TODO Добавить цвет папкам
                type = FolderType.TASKS,
                {}
            )
        }
    }
}