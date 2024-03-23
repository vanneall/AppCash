package com.example.appcash.view.tasks.task.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.data.entities.TaskWithTask
import com.example.appcash.domain.notes.implementations.GetFolderNameByIdUseCaseImpl
import com.example.appcash.domain.tasks.implementations.GetTaskUseCase
import com.example.appcash.domain.tasks.implementations.InsertMaintaskUseCaseImpl
import com.example.appcash.domain.tasks.implementations.UpdateTaskUseCaseImpl
import com.example.appcash.utils.ArgsKeys
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import com.example.appcash.utils.events.SearchEvent
import com.example.appcash.view.general.other.BottomSheetEvent
import com.example.appcash.view.notes.notes_folder.components.FolderOpenMode
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TasksViewModel @AssistedInject constructor(
    @Assisted(ArgsKeys.FOLDER_ID_KEY)
    private val folderId: Long,
    @Assisted(ArgsKeys.OPEN_MODE_KEY)
    private val mode: FolderOpenMode,
    private val getFolderNameByIdUseCase: GetFolderNameByIdUseCaseImpl,
    private val getTaskUseCase: GetTaskUseCase,
    private val insertMainTaskUseCase: InsertMaintaskUseCaseImpl,
    private val updateTaskUseCase: UpdateTaskUseCaseImpl
) : ViewModel(), EventHandler {

    private val _tasksMap = initializePrivateState()

    private val _searchQuery = MutableStateFlow("")

    private val _folderName = initializePrivateFolderName()

    private val _isError = MutableStateFlow(false)

    private val _isShowed = MutableStateFlow(Pair(false, false))

    val state = combine(
        _tasksMap,
        _searchQuery,
        _folderName,
        _isError,
        _isShowed
    ) { tasksMap, query, name, isError, isShowed ->
        TasksState(
            folderName = name,
            values = tasksMap.filter { it.text.contains(query) },
            querySearch = query,
            isError = isError,
            isShowed = isShowed
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TasksState())

    override fun handle(event: Event) {
        when (event) {
            is TaskEvent.CreateTask -> {
                insertTask(
                    text = event.text,
                    parentId = event.parentTaskId,
                    folderId = folderId
                )
            }

            is TaskEvent.UpdateCompletedState -> {
                updateTaskChecked(
                    id = event.id,
                    isChecked = event.isChecked
                )
            }

            is TaskEvent.ShowTaskBottomSheetEvent -> {
                showMaintaskBottomSheet()
            }

            is SearchEvent -> {
                updateQuery(query = event.query)
            }

            is BottomSheetEvent.HideEvent -> {
                updateHide()
            }

            is Event.ErrorEvent -> {
                updateIsError()
            }
        }
    }

    private fun insertTask(
        text: String,
        parentId: Long,
        folderId: Long,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            insertMainTaskUseCase(
                text = text,
                onError = ::handle,
                parentTaskId = parentId.takeIf { id -> id > 0 },
                folderId = folderId.takeIf { id -> id > 0 }
            )
        }
    }

    private fun showMaintaskBottomSheet() {
        _isShowed.update { it.copy(first = true) }
    }

    private fun updateTaskChecked(
        id: Long,
        isChecked: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            updateTaskUseCase.invoke(
                id = id,
                isChecked = isChecked,
                onError = ::handle
            )
        }
    }

    private fun updateQuery(query: String) {
        _searchQuery.update { query }
    }

    private fun updateHide() {
        _isShowed.update { Pair(false, false) }
    }

    private fun updateIsError() {
        _isError.update { true }
    }

    private fun initializePrivateState(): Flow<List<TaskWithTask>> {
        return getTaskUseCase.invoke(
            folderId = folderId.takeIf { folderId > 0 },
            onError = ::handle
        ).stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    }

    private fun initializePrivateFolderName(): Flow<String> {
        return when (mode) {
            FolderOpenMode.ALL -> {
                flowOf("Все задачи")
            }

            FolderOpenMode.DEFINED -> {
                getFolderNameByIdUseCase.invoke(id = folderId, onError = {})
            }

            else -> flowOf()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")
    }
}