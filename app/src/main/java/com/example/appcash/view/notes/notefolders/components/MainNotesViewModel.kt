package com.example.appcash.view.notes.notefolders.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.utils.FolderIconMapper
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
import ru.point.domain.notes.interfaces.GetAllNotesCountUseCase
import ru.point.domain.notes.interfaces.GetCategoryByTypeUseCase
import ru.point.domain.notes.interfaces.InsertFolderUseCase
import javax.inject.Inject

@HiltViewModel
class MainNotesViewModel @Inject constructor(
    getCategoryByTypeUseCase: GetCategoryByTypeUseCase,
    getAllNotesCountUseCase: GetAllNotesCountUseCase,
    private val insertFolderUseCase: Lazy<InsertFolderUseCase>,

    ) : ViewModel(), EventHandler {

    private val _notesCount = getAllNotesCountUseCase.invoke()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)


    private val _popupState = MutableStateFlow(CreateCategoryPopupState())

    private val _isShowed = MutableStateFlow(false)

    private val _foldersDtoList = getCategoryByTypeUseCase
        .invoke(
            type = Discriminator.NOTES
        ).stateIn(viewModelScope, SharingStarted.WhileSubscribed(), listOf())

    val state = combine(
        _foldersDtoList,
        _notesCount,
        _popupState,
        _isShowed
    ) { list, notesCount, popupState, isShowed ->
        MainNotesState(
            foldersList = list,
            notesCount = notesCount.toString(),
            popupState = popupState,
            isCreatePopupShowed = isShowed
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MainNotesState())

    override fun handle(event: Event) {
        when (event) {
            is CreateCategoryPopupEvent.InsertFolder -> {
                insertFolder(
                    name = event.name,
                    color = event.color
                )
            }

            is CreateCategoryPopupEvent.SelectFolderIcon -> {
                selectFolderIcon(event.position)
            }

            is CreateCategoryPopupEvent.ShowCreatePopup -> {
                showBottomSheet()
            }

            is CreateCategoryPopupEvent.HideCreatePopup -> {
                hideBottomSheet()
            }

            is CreateCategoryPopupEvent.InputName -> {
                inputFolderName(event.name)
            }
        }
    }

    private fun insertFolder(name: String, color: Int) {
        viewModelScope.launch(context = Dispatchers.IO) {
            insertFolderUseCase.get().invoke(
                name = name,
                colorIndex = color,
                discriminator = Discriminator.NOTES,
                iconId = _popupState.value.selectedFolderIcon,
            )
            hideBottomSheet()
        }
    }


    private fun selectFolderIcon(position: Int) {
        _popupState.update { state ->
            state.copy(
                selectedFolderIcon = FolderIconMapper.mapToFolderIcon(position = position)
            )
        }
    }

    private fun showBottomSheet() {
        _isShowed.update { true }
    }

    private fun inputFolderName(name: String) {
        _popupState.update { state ->
            state.copy(
                name = name
            )
        }
    }

    private fun hideBottomSheet() {
        _isShowed.update { false }
        _popupState.update { CreateCategoryPopupState() }
    }
}