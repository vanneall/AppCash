package com.example.appcash.view.tasks.all_tasks.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import com.example.appcash.view.popup.CreateCategoryPopupEvent
import com.example.appcash.view.popup.CreateCategoryPopupState
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.point.data.data.entities.Category.Discriminator
import ru.point.domain.notes.interfaces.GetCategoryByTypeUseCase
import ru.point.domain.notes.interfaces.InsertFolderUseCase
import ru.point.domain.tasks.interfaces.GetAllTasksCountUseCase
import ru.point.domain.tasks.interfaces.GetPlannedCountUseCase
import javax.inject.Inject

@HiltViewModel
class AllTasksFoldersViewModel @Inject constructor(
    getCategoryByTypeUseCase: GetCategoryByTypeUseCase,
    getPlannedCountUseCase: GetPlannedCountUseCase,
    getAllTasksCountUseCase: GetAllTasksCountUseCase,
    private val insertFolderUseCase: Lazy<InsertFolderUseCase>,
) : ViewModel(), EventHandler {

    private val _createPopupState = MutableStateFlow(CreateCategoryPopupState())

    private val _foldersList =
        getCategoryByTypeUseCase.invoke(type = Discriminator.TASKS)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _plannedCount = getPlannedCountUseCase.invoke()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)

    private val _completedCount = getAllTasksCountUseCase.invoke()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)

    val state = combine(
        _foldersList,
        _createPopupState,
        _plannedCount,
        _completedCount,
    ) { list, popupState, planned, completed ->
        AllTasksState(
            categories = list,
            createCategoryPopupState = popupState,
            plannedTasks = planned.toString(),
            bookmarkTasks = completed,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), AllTasksState())

    override fun handle(event: Event) {
        when (event) {
            is CreateCategoryPopupEvent.InsertFolder -> {
                insertFolder(
                    name = event.name,
                    color = event.color
                )
            }

            is CreateCategoryPopupEvent.InputName -> {
                inputFolderName(event.name)
            }

            is CreateCategoryPopupEvent.ShowCreatePopup -> {
                updateShow()
            }

            is CreateCategoryPopupEvent.HideCreatePopup -> {
                updateHide()
            }
        }
    }


    private fun insertFolder(name: String, color: Int) {
        viewModelScope.launch(context = Dispatchers.IO) {
            insertFolderUseCase.get().invoke(
                name = name,
                colorIndex = color,
                discriminator = Discriminator.TASKS,
                iconId = "game_folder_icon",
            )
            updateHide()
        }
    }

    private fun updateShow() {
        _createPopupState.update { state ->
            state.copy(
                isShowed = true
            )
        }
    }

    private fun inputFolderName(name: String) {
        _createPopupState.update { state ->
            state.copy(
                name = name
            )
        }
    }

    private fun updateHide() {
        _createPopupState.update { state ->
            state.copy(
                isShowed = false,
                name = ""
            )
        }
    }
}