package com.example.appcash.view.tasks.list.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.utils.ArgsKeys
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import com.example.appcash.view.popup.taskconfigurator.TaskConfiguratorPopupEvent
import com.example.appcash.view.popup.taskconfigurator.TaskConfiguratorPopupState
import com.example.appcash.view.popup.taskcontroll.TaskControlPopupEvent
import com.example.appcash.view.popup.taskcontroll.TaskControlPopupState
import dagger.Lazy
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.point.data.data.entity.entities.TaskWithTask
import ru.point.domain.category.implementations.GetCategoryNameByIdUseCaseImpl
import ru.point.domain.tasks.implementations.DeleteTaskByIdUseCaseImpl
import ru.point.domain.tasks.implementations.GetBookmarksTasksUseCaseImpl
import ru.point.domain.tasks.implementations.GetTaskUseCaseImpl
import ru.point.domain.tasks.implementations.InsertTaskUseCaseImpl
import ru.point.domain.tasks.implementations.UpdateTaskBookmarkedUseCaseImpl
import ru.point.domain.tasks.implementations.UpdateTaskCheckedUseCaseImpl
import ru.point.domain.tasks.implementations.UpdateTaskUseCaseImpl
import java.time.LocalDate

class TasksListViewModel @AssistedInject constructor(
    @Assisted(ArgsKeys.CATEGORY_ID_KEY)
    private val folderId: Long?,
    @Assisted(ArgsKeys.OPEN_MODE_KEY)
    private val mode: TasksSelection,
    private val getCategoryNameByIdUseCaseImpl: Lazy<GetCategoryNameByIdUseCaseImpl>,
    private val deleteTaskByIdUseCaseImpl: Lazy<DeleteTaskByIdUseCaseImpl>,
    private val getTaskUseCaseImpl: Lazy<GetTaskUseCaseImpl>,
    private val getBookmarksTaskUseCaseImpl: Lazy<GetBookmarksTasksUseCaseImpl>,
    private val insertTaskUseCaseImpl: Lazy<InsertTaskUseCaseImpl>,
    private val updateTaskCheckedUseCaseImpl: Lazy<UpdateTaskCheckedUseCaseImpl>,
    private val updateTaskBookmarkedUseCaseImpl: Lazy<UpdateTaskBookmarkedUseCaseImpl>,
    private val updateTaskUseCaseImpl: Lazy<UpdateTaskUseCaseImpl>
) : ViewModel(), EventHandler {

    private val _tasks = initializeTasksList()
    private val _screenTitle = initializeScreenTitle()
    private val _editPopupState = initializeEditPopupState()
    private val _configPopupState = initializeConfigPopupState()

    val state = combine(
        _tasks,
        _screenTitle,
        _editPopupState,
        _configPopupState
    ) { tasks, screenTitle, editPopupState, configPopupState ->
        TasksListState(
            tasks = tasks,
            screenTitle = screenTitle,
            taskConfiguratorPopupState = editPopupState,
            taskControlPopupState = configPopupState
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TasksListState())

    override fun handle(event: Event) {
        when (event) {

            is TaskConfiguratorPopupEvent -> handleEditPopupEvent(event = event)

            is TaskControlPopupEvent -> handleConfigPopupEvent(event = event)

            is TaskListEvent.UpdateCompletedState -> {
                updateTaskChecked(
                    id = event.id,
                    isChecked = event.isChecked
                )
            }

            is TaskListEvent.UpdateBookmarked -> {
                updateBookmark(id = event.id)
            }
        }
    }

    private fun handleEditPopupEvent(event: TaskConfiguratorPopupEvent) {
        when (event) {
            is TaskConfiguratorPopupEvent.CreateTask -> {
                upsertTask(parentId = event.parentTaskId,)
            }

            is TaskConfiguratorPopupEvent.ShowPopup -> {
                showEditPopup(
                    id = null,
                    parentId = event.parentId,
                    name = event.name,
                    description = event.description,
                    date = event.date
                )
            }

            is TaskConfiguratorPopupEvent.HidePopup -> {
                hideEditPopup()
            }

            is TaskConfiguratorPopupEvent.InsertName -> {
                inputNamePopup(event.name)
            }

            is TaskConfiguratorPopupEvent.InsertDescription -> {
                inputDescriptionPopup(event.description)
            }

            is TaskConfiguratorPopupEvent.ShowDatePickerDialog -> {
                showDatePickerDialog()
            }

            is TaskConfiguratorPopupEvent.HideDatePickerDialog -> {
                hideDatePickerDialog()
            }

            is TaskConfiguratorPopupEvent.SelectDate -> {
                updateSelectedDate(newDate = event.localDate)
            }
        }
    }

    private fun handleConfigPopupEvent(event: TaskControlPopupEvent) {
        when (event) {
            is TaskControlPopupEvent.ShowPopup -> {
                showConfigPopup(
                    id = event.id,
                    name = event.name,
                    description = event.description,
                    localDate = event.localDate
                )
            }

            is TaskControlPopupEvent.HidePopup -> {
                hideConfigPopup()
            }

            is TaskControlPopupEvent.DeleteTask -> {
                deleteTaskById(id = event.id)
            }

            is TaskControlPopupEvent.EditTask -> {
                hideConfigPopup()
                viewModelScope.launch {
                    _tasks.collect { list ->
                        val task = list.first { task -> task.id == event.id }
                        showEditPopup(
                            id = task.id,
                            parentId = null,
                            name = task.text,
                            description = null,
                            date = LocalDate.now()
                        )
                    }
                }
            }
        }
    }

    private fun updateSelectedDate(newDate: LocalDate) {
        _editPopupState.update { state ->
            state.copy(date = newDate)
        }
    }

    private fun updateBookmark(id: Long) {
        updateTaskBookmarkedUseCaseImpl.get().invoke(id = id)
    }

    private fun upsertTask(
        parentId: Long?
    ) {
        with(_editPopupState.value) {
            if (name.isEmpty()) {
                _editPopupState.update { state -> state.copy(isNameError = true) }
                return
            }
            if (id == null) {
                insertTaskUseCaseImpl.get().invoke(
                    text = name,
                    description = description,
                    parentTaskId = parentId,
                    categoryId = folderId,
                    date = date ?: LocalDate.now()
                )
            } else {
                updateTaskUseCaseImpl.get().invoke(
                    id = id,
                    text = name,
                    description = description,
                    date = date ?: LocalDate.now()
                )
            }
            hideEditPopup()
        }
    }

    private fun showEditPopup(
        id: Long?,
        parentId: Long?,
        name: String?,
        description: String?,
        date: LocalDate
    ) {
        _editPopupState.update { state ->
            state.copy(
                id = id,
                parentId = parentId,
                name = name ?: "",
                description = description ?: "",
                isShowed = true,
                date = date
            )
        }
    }

    private fun showDatePickerDialog() {
        _editPopupState.update { state ->
            state.copy(isDatePickerShowed = true)
        }
    }

    private fun hideDatePickerDialog() {
        _editPopupState.update { state ->
            state.copy(isDatePickerShowed = false)
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
        _editPopupState.update { TaskConfiguratorPopupState() }
    }

    private fun hideConfigPopup() {
        _configPopupState.update { TaskControlPopupState() }
    }

    private fun showConfigPopup(
        id: Long,
        name: String,
        description: String?,
        localDate: LocalDate?
    ) {
        _configPopupState.update { state ->
            state.copy(
                isShowed = true,
                id = id,
                name = name,
                description = description,
                localDate = localDate
            )
        }
    }

    private fun updateTaskChecked(
        id: Long,
        isChecked: Boolean
    ) {
        updateTaskCheckedUseCaseImpl.get().invoke(
            id = id,
            isChecked = isChecked,
        )
    }

    private fun deleteTaskById(id: Long) {
        deleteTaskByIdUseCaseImpl.get().invoke(id = id)
        hideConfigPopup()
    }

    private fun initializeTasksList(): Flow<List<TaskWithTask>> {
        return when (mode) {
            TasksSelection.ALL -> getTaskUseCaseImpl
                .get()
                .invoke(folderId = null)

            TasksSelection.ONLY_CATEGORY -> getTaskUseCaseImpl
                .get()
                .invoke(folderId = folderId)

            TasksSelection.ONLY_BOOKMARKS -> getBookmarksTaskUseCaseImpl
                .get()
                .invoke()

            else -> flowOf()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    }

    private fun initializeConfigPopupState(): MutableStateFlow<TaskControlPopupState> {
        return MutableStateFlow(TaskControlPopupState())
    }

    private fun initializeEditPopupState(): MutableStateFlow<TaskConfiguratorPopupState> {
        return MutableStateFlow(TaskConfiguratorPopupState())
    }

    private fun initializeScreenTitle(): Flow<String> {
        return when (mode) {
            TasksSelection.ALL -> {
                flowOf(ALL)
            }

            TasksSelection.ONLY_CATEGORY -> {
                if (folderId != null) getCategoryNameByIdUseCaseImpl.get().invoke(id = folderId)
                else flowOf(ERROR)
            }

            TasksSelection.ONLY_BOOKMARKS -> {
                flowOf(BOOKMARKS)
            }

            else -> flowOf(ERROR)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")
    }

    companion object {
        const val ALL = "ALL"
        const val BOOKMARKS = "BOOKMARKS"
        const val ERROR = "ERROR"
    }
}