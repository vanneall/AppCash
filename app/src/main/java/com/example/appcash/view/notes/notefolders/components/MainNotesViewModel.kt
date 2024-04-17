package com.example.appcash.view.notes.notefolders.components

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
import ru.point.data.data.entity.entities.Category
import ru.point.data.data.entity.entities.Category.Discriminator
import ru.point.domain.category.interfaces.CreateCategoryUseCase
import ru.point.domain.category.interfaces.GetCategoryByTypeUseCase
import ru.point.domain.notes.interfaces.GetAllNotesCountUseCase
import javax.inject.Inject

@HiltViewModel
class MainNotesViewModel @Inject constructor(
    private val getCategoryByTypeUseCase: GetCategoryByTypeUseCase,
    private val getAllNotesCountUseCase: GetAllNotesCountUseCase,
    private val createCategoryUseCase: Lazy<CreateCategoryUseCase>,
) : ViewModel(), EventHandler {

    private val _allNotesCount = initializeAllNoteCount()

    private val _categoriesList = initializeCategoriesList()

    private val _categoryCreatePopupState = MutableStateFlow(CreateCategoryPopupState())

    val state = combine(
        _categoriesList,
        _allNotesCount,
        _categoryCreatePopupState,
    ) { list, notesCount, popupState ->
        MainNotesState(
            categoryList = list,
            notesCount = notesCount.toString(),
            popupState = popupState,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MainNotesState())

    override fun handle(event: Event) {
        when (event) {
            is CreateCategoryPopupEvent.CreateCategory -> {
                createCategory()
            }

            is CreateCategoryPopupEvent.SelectIcon -> {
                updateFolderIcon(position = event.position)
            }

            is CreateCategoryPopupEvent.SelectColor -> {
                updateFolderColor(
                    color = event.color,
                    index = event.index
                )
            }

            is CreateCategoryPopupEvent.ShowCreatePopup -> {
                showBottomSheet()
            }

            is CreateCategoryPopupEvent.HideCreatePopup -> {
                hideBottomSheet()
            }

            is CreateCategoryPopupEvent.InputName -> {
                updateFolderName(name = event.name)
            }
        }
    }

    private fun initializeCategoriesList(): Flow<List<Category>> {
        return getCategoryByTypeUseCase
            .invoke(type = Discriminator.Note)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    }

    private fun initializeAllNoteCount(): Flow<Int> {
        return getAllNotesCountUseCase
            .invoke()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)
    }

    private fun updateFolderName(name: String) {
        _categoryCreatePopupState.update { state ->
            state.copy(name = name)
        }
    }

    private fun updateFolderIcon(position: Int) {
        _categoryCreatePopupState.update { state ->
            state.copy(
                selectedFolderIcon = FolderIconMapper.mapToFolderIcon(position = position),
                selectedIconIndex = position
            )
        }
    }

    private fun updateFolderColor(color: Int, index: Int) {
        _categoryCreatePopupState.update { state ->
            state.copy(
                selectedColor = color,
                selectedColorIndex = index
            )
        }
    }

    private fun createCategory() {
        with(_categoryCreatePopupState.value) {
            if (name.isEmpty()) {
                _categoryCreatePopupState.update { state -> state.copy(isNameTextFieldError = true) }
            } else {
                createCategoryUseCase.get().invoke(
                    name = name,
                    color = selectedColor,
                    discriminator = Discriminator.Note,
                    iconId = selectedFolderIcon,
                )
                hideBottomSheet()
            }
        }
    }

    private fun showBottomSheet() {
        _categoryCreatePopupState.update { state ->
            state.copy(isShowed = true)
        }
    }

    private fun hideBottomSheet() {
        _categoryCreatePopupState.update {
            CreateCategoryPopupState()
        }
    }
}