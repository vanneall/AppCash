package com.example.appcash.view.tasks.main.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.utils.FolderIconMapper
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import com.example.appcash.view.popup.create.CreateCategoryPopupEvent
import com.example.appcash.view.popup.create.CreateCategoryPopupState
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ru.point.data.data.entities.Category
import ru.point.data.data.entities.Category.Discriminator
import ru.point.domain.category.interfaces.CreateCategoryUseCase
import ru.point.domain.category.interfaces.GetCategoryByTypeUseCase
import ru.point.domain.tasks.interfaces.GetAllTasksCountUseCase
import ru.point.domain.tasks.interfaces.GetBookmarksCountUseCase
import javax.inject.Inject

@HiltViewModel
class TasksMainViewModel @Inject constructor(
    private val getCategoryByTypeUseCase: GetCategoryByTypeUseCase,
    private val getBookmarksCountUseCase: GetBookmarksCountUseCase,
    private val getAllTasksCountUseCase: GetAllTasksCountUseCase,
    private val createCategoryUseCase: Lazy<CreateCategoryUseCase>,
) : ViewModel(), EventHandler {

    private val _createPopupState = MutableStateFlow(CreateCategoryPopupState())

    private val _categories = initializeCategoriesList()

    private val _bookmarkTasksCount = initializeBookmarksCountList()

    private val _allTasksCount = initializeAllTasksCountList()

    val state = combine(
        _categories,
        _createPopupState,
        _allTasksCount,
        _bookmarkTasksCount
    ) { list, popupState, allTasks, bookMarksTask ->
        TasksMainState(
            categories = list,
            createCategoryPopupState = popupState,
            allTasksCount = allTasks.toString(),
            bookmarkTasksCount = bookMarksTask.toString(),
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TasksMainState())

    override fun handle(event: Event) {
        when (event) {
            is CreateCategoryPopupEvent.CreateCategory -> {
                createCategory(
                    name = event.name,
                    color = event.color
                )
            }

            is CreateCategoryPopupEvent.SelectCategoryIcon -> {
                updateFolderIcon(event.position)
            }

            is CreateCategoryPopupEvent.InputName -> {
                updateCategoryName(event.name)
            }

            is CreateCategoryPopupEvent.ShowCreatePopup -> {
                showCreatePopup()
            }

            is CreateCategoryPopupEvent.HideCreatePopup -> {
                hideCategoryPopup()
            }
        }
    }

    private fun initializeCategoriesList(): Flow<List<Category>> {
        return getCategoryByTypeUseCase
            .invoke(type = Discriminator.Task)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    }

    private fun initializeBookmarksCountList(): Flow<Int> {
        return getBookmarksCountUseCase
            .invoke()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)
    }

    private fun initializeAllTasksCountList(): Flow<Int> {
        return getAllTasksCountUseCase
            .invoke()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)
    }

    private fun createCategory(name: String, color: Int) {
        createCategoryUseCase.get().invoke(
            name = name,
            colorIndex = color,
            discriminator = Discriminator.Task,
            iconId = _createPopupState.value.selectedFolderIcon,
        )
        hideCategoryPopup()
    }

    private fun updateFolderIcon(position: Int) {
        _createPopupState.update { state ->
            state.copy(
                selectedFolderIcon = FolderIconMapper.mapToFolderIcon(position = position)
            )
        }
    }

    private fun showCreatePopup() {
        _createPopupState.update { state ->
            state.copy(isShowed = true)
        }
    }

    private fun updateCategoryName(name: String) {
        _createPopupState.update { state ->
            state.copy(name = name)
        }
    }

    private fun hideCategoryPopup() {
        _createPopupState.update { CreateCategoryPopupState() }
    }
}