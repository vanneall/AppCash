package com.example.appcash.view.tasks.task.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.utils.ArgsKeys
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import com.example.appcash.view.notes.notefolders.components.FolderOpenMode
import com.example.appcash.view.popup.ConfigPopupEvent
import com.example.appcash.view.popup.ConfigPopupState
import com.example.appcash.view.popup.EditPopupEvent
import com.example.appcash.view.popup.EditPopupState
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
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
import ru.point.domain.tasks.implementations.DeleteTaskByIdUseCaseImpl
import ru.point.domain.tasks.implementations.GetTaskUseCase
import ru.point.domain.tasks.implementations.InsertTaskUseCaseImpl
import ru.point.domain.tasks.implementations.UpdateTaskUseCaseImpl

class TasksViewModel @AssistedInject constructor(
    @Assisted(ArgsKeys.FOLDER_ID_KEY)
    private val folderId: Long,
    @Assisted(ArgsKeys.OPEN_MODE_KEY)
    private val mode: FolderOpenMode,
    private val getFolderNameByIdUseCase: GetCategoryNameByIdUseCaseImpl,
    private val deleteTaskByIdUseCaseImpl: DeleteTaskByIdUseCaseImpl,
    private val getTaskUseCase: GetTaskUseCase,
    private val insertMainTaskUseCase: InsertTaskUseCaseImpl,
    private val updateTaskUseCase: UpdateTaskUseCaseImpl
) : ViewModel(), EventHandler {

    private val _tasksMap = initializePrivateState()

    private val _folderName = initializePrivateFolderName()

    private val _editPopupState = MutableStateFlow(EditPopupState())

    private val _configPopupState = MutableStateFlow(ConfigPopupState())

    val state = combine(
        _tasksMap,
        _folderName,
        _editPopupState,
        _configPopupState
    ) { tasksMap, name, editState, configState ->
        TasksState(
            folderName = name,
            values = tasksMap,
            editPopupState = editState,
            configPopupState = configState
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TasksState())

    override fun handle(event: Event) {
        when (event) {
            is EditPopupEvent.CreateTask -> {
                insertTask(
                    text = _editPopupState.value.name,
                    parentId = event.parentTaskId,
                    folderId = folderId
                )
            }

            is EditPopupEvent.ShowPopup -> {
                showEditPopup(
                    id = 0,
                    parentId = event.parentId,
                    name = event.name,
                    description = event.description,
                    date = event.date
                )
            }

            is EditPopupEvent.HidePopup -> {
                hideEditPopup()
            }

            is EditPopupEvent.InsertName -> {
                inputNamePopup(event.name)
            }

            is EditPopupEvent.InsertDescription -> {
                inputDescriptionPopup(event.name)
            }

            is TaskEvent.UpdateCompletedState -> {
                updateTaskChecked(
                    id = event.id,
                    isChecked = event.isChecked
                )
            }

            is ConfigPopupEvent.ShowPopup -> {
                showConfigPopup(event.id, event.name)
            }

            is ConfigPopupEvent.HidePopup -> {
                hideConfigPopup()
            }

            is ConfigPopupEvent.DeleteTask -> {
                deleteTaskById(id = event.id)
            }

            is ConfigPopupEvent.EditTask -> {
                hideConfigPopup()
                viewModelScope.launch {
                    _tasksMap.collect { list ->
                        val task = list.first { task -> task.id == event.id }
                        showEditPopup(
                            id = task.id,
                            parentId = null,
                            name = task.text,
                            description = null,
                            date = null
                        )
                    }
                }
            }
        }
    }

    private fun insertTask(
        text: String,
        parentId: Long?,
        folderId: Long?,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            insertMainTaskUseCase(
                text = text,
                parentTaskId = parentId,
                folderId = folderId
            )
            hideEditPopup()
        }
    }

    private fun showEditPopup(
        id: Long?,
        parentId: Long?,
        name: String?,
        description: String?,
        date: String?
    ) {
        _editPopupState.update { state ->
            state.copy(
                parentId = parentId,
                name = name ?: "",
                description = description ?: "",
                isShowed = true,
                date = date ?: ""
            )
        }
    }

    private fun inputNamePopup(name: String) {
        _editPopupState.update { state ->
            state.copy(name = name)
        }
    }

    private fun inputDescriptionPopup(description: String) {
        _editPopupState.update { state ->
            state.copy(description = description)
        }
    }

    private fun hideEditPopup() {
        _editPopupState.update { state ->
            state.copy(isShowed = false)
        }
    }

    private fun hideConfigPopup() {
        _configPopupState.update { state ->
            state.copy(
                isShowed = false,
                id = 0,
                name = ""
            )
        }
    }

    private fun showConfigPopup(
        id: Long,
        name: String,
    ) {
        _configPopupState.update { state ->
            state.copy(
                isShowed = true,
                id = id,
                name = name
            )
        }
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

    private fun deleteTaskById(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            deleteTaskByIdUseCaseImpl.invoke(id = id)
        }
        hideConfigPopup()
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