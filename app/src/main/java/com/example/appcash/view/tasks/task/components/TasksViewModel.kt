package com.example.appcash.view.tasks.task.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.entities.SubTask
import com.example.appcash.domain.notes.implementations.GetFolderNameByIdUseCaseImpl
import com.example.appcash.domain.tasks.implementations.GetMapTasksByFolderIdUseCaseImpl
import com.example.appcash.domain.tasks.implementations.GetMapTasksUseCaseImpl
import com.example.appcash.domain.tasks.implementations.InsertMaintaskUseCaseImpl
import com.example.appcash.domain.tasks.implementations.InsertSubtaskUseCaseImpl
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
    private val insertTaskToFolderLinkUseCaseImpl: InsertTaskToFolderLinkUseCaseImpl,
    private val getFolderNameByIdUseCase: GetFolderNameByIdUseCaseImpl,
    private val getMapTasksUseCase: GetMapTasksUseCaseImpl,
    private val getMapTasksByFolderIdUseCase: GetMapTasksByFolderIdUseCaseImpl,
    private val insertMainTaskUseCase: InsertMaintaskUseCaseImpl,
    private val insertSubTaskUseCase: InsertSubtaskUseCaseImpl,
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
            values = tasksMap.filterKeys { it.text.contains(query) },
            querySearch = query,
            isError = isError,
            isShowed = isShowed
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TasksState())

    override fun handle(event: Event) {
        when (event) {
            is TaskEvent.CreateMaintaskEvent -> {
                insertMaintask(text = event.text)
            }

            is TaskEvent.CreateSubtaskEvent -> {
                insertSubtask(
                    mainTaskId = event.mainTaskId,
                    text = event.text
                )
            }

            is TaskEvent.UpdateCheckBoxEvent -> {
                updateTaskChecked(
                    id = event.id,
                    isChecked = event.isChecked,
                    type = event.type
                )
            }

            is TaskEvent.ShowMaintaskBottomSheetEvent -> {
                showMaintaskBottomSheet()
            }

            is TaskEvent.ShowSubtaskBottomSheetEvent -> {
                showSubtaskBottomSheet()
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

    private fun insertMaintask(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            insertMainTaskUseCase(
                folderId = folderId,
                text = text,
                onError = ::handle
            )
        }
    }

    private fun insertSubtask(
        mainTaskId: Long,
        text: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            insertSubTaskUseCase(
                mainTaskId = mainTaskId,
                text = text,
                onError = ::handle
            )
        }
    }

    private fun showMaintaskBottomSheet() {
        _isShowed.update { it.copy(first = true) }
    }

    private fun showSubtaskBottomSheet() {
        _isShowed.update { it.copy(second = true) }
    }

    private fun updateTaskChecked(
        id: Long,
        isChecked: Boolean,
        type: TaskType
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            updateTaskUseCase.invoke(
                id = id,
                isChecked = isChecked,
                type = type,
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

    private fun initializePrivateState(): Flow<Map<MainTask, List<SubTask>?>> {
        return when (mode) {
            FolderOpenMode.ALL -> {
                getMapTasksUseCase.invoke()
            }

            FolderOpenMode.DEFINED -> {
                getMapTasksByFolderIdUseCase.invoke(id = folderId)
            }

            else -> flowOf()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())
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