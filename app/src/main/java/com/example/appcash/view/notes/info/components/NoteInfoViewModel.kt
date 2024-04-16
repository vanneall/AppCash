package com.example.appcash.view.notes.info.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.utils.ArgsKeys.CATEGORY_ID_KEY
import com.example.appcash.utils.ArgsKeys.ID_KEY
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import dagger.Lazy
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.point.domain.notes.implementations.GetNoteByIdUseCaseImpl
import ru.point.domain.notes.implementations.UpsertNoteUseCaseImpl

class NoteInfoViewModel @AssistedInject constructor(
    @Assisted(ID_KEY)
    private val noteId: Long?,
    @Assisted(CATEGORY_ID_KEY)
    private val folderId: Long?,
    private val getNoteByIdUseCaseImpl: Lazy<GetNoteByIdUseCaseImpl>,
    private val upsertNoteUseCaseImpl: Lazy<UpsertNoteUseCaseImpl>,
) : ViewModel(), EventHandler {

    private val _title = MutableStateFlow("")
    private val _content = MutableStateFlow("")

    init {
        if (noteId != null) initializeTitleAndContent(id = noteId)
    }

    val state = combine(
        _title,
        _content,
    ) { title, content ->
        NoteInfoState(
            title = title,
            content = content
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), NoteInfoState())

    override fun handle(event: Event) {
        when (event) {
            is NoteInfoEvent.InputTitleEvent -> {
                updateTitle(title = event.title)
            }

            is NoteInfoEvent.InputContentEvent -> {
                updateContent(content = event.content)
            }

            is NoteInfoEvent.SaveNoteEvent -> {
                saveNote()
            }
        }
    }

    private fun updateTitle(title: String) {
        _title.update { title }
    }

    private fun updateContent(content: String) {
        _content.update { content }
    }

    private fun saveNote() {
        CoroutineScope(Dispatchers.IO).launch {
            with(state.value) {
                upsertNote(title = title, content = content)
            }
        }
    }

    private fun initializeTitleAndContent(id: Long) {
        viewModelScope.launch {
            getNoteByIdUseCaseImpl
                .get()
                .invoke(id = id)
                .collect { note ->
                    updateTitle(note.title)
                    updateContent(note.content)
                }
        }
    }

    private fun upsertNote(title: String, content: String) {
        upsertNoteUseCaseImpl.get().invoke(
            id = noteId,
            title = title,
            content = content,
            categoryId = folderId,
        )
    }
}