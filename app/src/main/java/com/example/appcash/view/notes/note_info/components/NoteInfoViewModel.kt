package com.example.appcash.view.notes.note_info.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.data.entities.Note
import com.example.appcash.domain.notes.implementations.GetNoteByIdUseCaseImpl
import com.example.appcash.view.Event
import com.example.appcash.view.EventHandler
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class NoteInfoViewModel @AssistedInject constructor(
    getNoteByIdUseCaseImpl: GetNoteByIdUseCaseImpl,
    @Assisted
    private val id: Long
) : ViewModel(), EventHandler {

    private val _state = getNoteByIdUseCaseImpl.invoke(id = id)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), Note(title = "", content = ""))

    private val _title = MutableStateFlow<String?>(null)

    private val _content = MutableStateFlow<String?>(null)

    val state = combine(_state, _title, _content) { state, title, content ->
        NoteInfoState(
            title = title ?: state.title,
            content = content ?: state.content
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), NoteInfoState())

    override fun handle(event: Event) {
        when (event) {
            is NoteInfoEvent.InputTitleEvent -> {
                _title.update {
                    event.title
                }
            }

            is NoteInfoEvent.InputContentEvent -> {
                _content.update {
                    event.content
                }
            }
        }
    }
}