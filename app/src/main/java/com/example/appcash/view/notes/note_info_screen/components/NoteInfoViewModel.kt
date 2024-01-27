package com.example.appcash.view.notes.note_info_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.data.entities.Note
import com.example.appcash.domain.notes.implementations.GetNoteByIdUseCaseImpl
import com.example.appcash.domain.notes.implementations.UpsertNoteUseCaseImpl
import com.example.appcash.utils.ArgsKeys
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
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

class NoteInfoViewModel @AssistedInject constructor(
    private val getNoteByIdUseCaseImpl: GetNoteByIdUseCaseImpl,
    private val upsertNoteUseCaseImpl: UpsertNoteUseCaseImpl,
    @Assisted
    private val mode: NoteOpenMode,
    @Assisted(ArgsKeys.ID_KEY)
    private val id: Long,
    @Assisted(ArgsKeys.FOLDER_ID_KEY)
    private val folderId: Long,
) : ViewModel(), EventHandler {

    private val _title = MutableStateFlow<String?>(null)

    private val _content = MutableStateFlow<String?>(null)

    private val _state = initializePrivateState()

    val state = combine(_state, _title, _content) { state, title, content ->
        NoteInfoState(
            title = title ?: state.title,
            content = content ?: state.content,
            folderId = folderId
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

            is NoteInfoEvent.SaveNoteEvent -> {
                CoroutineScope(Dispatchers.IO).launch {
                    upsertNote(title = event.title, content = event.content)
                }
            }
        }
    }

    private fun initializePrivateState(): Flow<Note> {
        return when(mode) {
            NoteOpenMode.CREATE -> {
                flowOf(
                    value = Note(
                        title = "",
                        content = ""
                    )
                )
            }
            NoteOpenMode.EDIT -> {
                getNoteByIdUseCaseImpl.invoke(id = id)
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(),
                        initialValue = Note(
                            title = "",
                            content = ""
                        )
                    )
            }
        }
    }

    private suspend fun upsertNote(title: String, content: String) {
        val handledTitle = title.trim()
        val handledContent = content.trim()

        if (handledTitle.isEmpty()) return

        when (mode) {
            NoteOpenMode.CREATE -> {
                upsertNoteUseCaseImpl.invoke(
                    note = Note(
                        title = handledTitle,
                        content = handledContent
                    ),
                    folderId = folderId
                )
            }
            NoteOpenMode.EDIT -> {
                _state.collect { state ->
                    upsertNoteUseCaseImpl.invoke(
                        Note(
                            id = state.id,
                            title = handledTitle,
                            content = handledContent
                        )
                    )
                }
            }
        }
    }
}