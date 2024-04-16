package com.example.appcash.view.notes.notes.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.utils.ArgsKeys.CATEGORY_ID_KEY
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import ru.point.data.data.entities.Note
import ru.point.domain.notes.implementations.GetCategoryNameByIdUseCaseImpl
import ru.point.domain.notes.implementations.GetNotesUseCaseImpl


class NotesListViewModel @AssistedInject constructor(
    @Assisted(CATEGORY_ID_KEY)
    private val categoryId: Long?,
    private val getCategoryNameByIdUseCase: GetCategoryNameByIdUseCaseImpl,
    private val getAllNotesUseCase: GetNotesUseCaseImpl,
) : ViewModel(), EventHandler {

    private val _notesList = initializeNotesList()
    private val _categoryName = initializeCategoryName()

    val state = combine(
        _categoryName,
        _notesList,
    ) { categoryName, notes ->
        NotesListState(
            categoryId = categoryId,
            categoryName = categoryName,
            notesList = notes,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), NotesListState())

    override fun handle(event: Event) {}

    private fun initializeNotesList(): Flow<List<Note>> {
        return getAllNotesUseCase
            .invoke(folderId = categoryId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    }

    private fun initializeCategoryName(): Flow<String> {
        return when (categoryId) {
            null -> {
                flowOf("Все заметки")
            }

            else -> {
                getCategoryNameByIdUseCase(categoryId)
            }
        }
    }
}




