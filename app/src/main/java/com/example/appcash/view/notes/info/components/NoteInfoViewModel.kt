package com.example.appcash.view.notes.info.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.utils.ArgsKeys.FOLDER_ID_KEY
import com.example.appcash.utils.ArgsKeys.ID_KEY
import com.example.appcash.utils.ArgsKeys.OPEN_MODE_KEY
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.Event.ErrorEvent
import com.example.appcash.utils.events.EventHandler
import dagger.Lazy
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.point.data.data.entities.Note
import ru.point.domain.notes.implementations.DeleteNoteByIdUseCaseImpl
import ru.point.domain.notes.implementations.GetNoteByIdUseCaseImpl
import ru.point.domain.notes.implementations.UpsertNoteUseCaseImpl

class NoteInfoViewModel @AssistedInject constructor(
    @Assisted(ID_KEY)
    private val noteId: Long,
    @Assisted(OPEN_MODE_KEY)
    private val openMode: NoteOpenMode,
    @Assisted(FOLDER_ID_KEY)
    private val folderId: Long,
    private val getNoteByIdUseCaseImpl: GetNoteByIdUseCaseImpl,
    private val upsertNoteUseCaseImpl: UpsertNoteUseCaseImpl,
    private val deleteNoteByIdUseCaseImpl: Lazy<DeleteNoteByIdUseCaseImpl>
) : ViewModel(), EventHandler {

    private val _title = MutableStateFlow<String?>(null)

    private val _content = MutableStateFlow<String?>(null)

    private val _note = initializePrivateState()

    private val _error = MutableStateFlow(false)

    val state = combine(
        _note,
        _title,
        _content,
        _error
    ) { state, title, content, error ->
        NoteInfoState(
            title = title ?: state.title,
            content = content ?: state.content,
            folderId = folderId,
            isError = error
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), NoteInfoState())

    override fun handle(event: Event) {
        when (event) {
            is NoteInfoEvent.InputTitleEvent -> {
                inputTitle(title = event.title)
            }

            is NoteInfoEvent.InputContentEvent -> {
                inputContent(content = event.content)
            }

            is NoteInfoEvent.SaveNoteEvent -> {
                saveNote(
                    title = state.value.title,
                    content = state.value.content
                )
            }

            is NoteInfoEvent.DeleteNoteEvent -> {
                deleteNote()
            }

            is ErrorEvent -> {
                updateIsError()
            }
        }
    }

    private fun inputTitle(title: String) {
        _title.update { title }
    }

    private fun inputContent(content: String) {
        _content.update { content }
    }

    private fun saveNote(title: String, content: String) {
        CoroutineScope(Dispatchers.IO).launch {
            upsertNote(title = title, content = content)
        }
    }

    private fun updateIsError() {
        _error.update { true }
    }

    private fun deleteNote() {
        CoroutineScope(Dispatchers.IO).launch {
            if (noteId == (0).toLong()) return@launch
            delay(1000L)
            deleteNoteByIdUseCaseImpl.get().invoke(
                id = noteId
            )
        }

    }

    private fun initializePrivateState(): Flow<Note> {
        return when (openMode) {
            NoteOpenMode.CREATE -> {
                flowOf(
                    value = Note(
                        title = "",
                        content = ""
                    )
                )
            }

            NoteOpenMode.EDIT -> {
                getNoteByIdUseCaseImpl.invoke(
                    id = noteId,
                ).stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(),
                    initialValue = Note(
                        title = "",
                        content = ""
                    )
                )
            }

            else -> {
                handle(event = ErrorEvent)
                emptyFlow()
            }
        }
    }

    private fun upsertNote(title: String, content: String) {
        val handledTitle = title.trim()
        val handledContent = content.trim()

        if (handledTitle.isEmpty()) return

        if (noteId == (-1).toLong()) {
            upsertNoteUseCaseImpl.invoke(
                title = title,
                content = handledContent,
                folderId = folderId.takeIf { folderId > 0 },
            )
        } else {
            upsertNoteUseCaseImpl.invoke(
                id = noteId,
                title = title,
                content = handledContent,
                folderId = folderId.takeIf { folderId > 0 },
            )
        }
    }
}