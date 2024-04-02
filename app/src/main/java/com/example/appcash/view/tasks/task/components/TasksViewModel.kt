package com.example.appcash.view.tasks.task.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.utils.ArgsKeys
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import com.example.appcash.utils.events.SearchEvent
import com.example.appcash.view.notes.notefolders.components.FolderOpenMode
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
import ru.point.data.data.entities.TaskWithTask
import ru.point.domain.notes.implementations.GetCategoryNameByIdUseCaseImpl
import ru.point.domain.tasks.implementations.GetTaskUseCase
import ru.point.domain.tasks.implementations.InsertTaskUseCaseImpl
import ru.point.domain.tasks.implementations.UpdateTaskUseCaseImpl

class TasksViewModel @AssistedInject constructor(
    @Assisted(ArgsKeys.FOLDER_ID_KEY)
    private val folderId: Long,
    @Assisted(ArgsKeys.OPEN_MODE_KEY)
    private val mode: FolderOpenMode,
    private val getFolderNameByIdUseCase: GetCategoryNameByIdUseCaseImpl,
    private val getTaskUseCase: GetTaskUseCase,
    private val insertMainTaskUseCase: InsertTaskUseCaseImpl,
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
        ).stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    }

    private fun initializePrivateFolderName(): Flow<String> {
        return when (mode) {
            FolderOpenMode.ALL -> {
                flowOf("Все задачи")
            }

            FolderOpenMode.DEFINED -> {
                getFolderNameByIdUseCase.invoke(id = folderId)
            }

            else -> flowOf()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")
    }
}