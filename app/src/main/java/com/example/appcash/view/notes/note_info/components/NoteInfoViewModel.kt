package com.example.appcash.view.notes.note_info.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.data.entities.Note
import com.example.appcash.domain.notes.implementations.GetNoteByIdUseCaseImpl
import com.example.appcash.domain.notes.implementations.InsertNoteUseCaseImpl
import com.example.appcash.domain.notes.implementations.UpdateNoteUseCaseImpl
import com.example.appcash.view.Event
import com.example.appcash.view.EventHandler
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class NoteInfoViewModel @AssistedInject constructor(
    getNoteByIdUseCaseImpl: GetNoteByIdUseCaseImpl,
    private val insertNoteUseCaseImpl: InsertNoteUseCaseImpl,
    private val updateNoteUseCaseImpl: UpdateNoteUseCaseImpl,
    @Assisted("id")
    private val id: Long,
    @Assisted("folderId")
    private val folderId: Long
) : ViewModel(), EventHandler {

    private val _state = if (id > -1) {
        getNoteByIdUseCaseImpl.invoke(id = id)
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                Note(title = "", content = "")
            )
    } else {
        flowOf(Note(title = "", content = ""))
    }

    private val _title = MutableStateFlow<String?>(null)

    private val _content = MutableStateFlow<String?>(null)

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
                    if (event.title.isNotEmpty() &&
                        id == (-1).toLong()
                    ) {
                        insertNoteUseCaseImpl.invoke(
                            note = Note(
                                title = event.title,
                                content = event.content
                            ),
                            folderId = folderId
                        )
                    } else if (event.title.isNotEmpty()) {
                        _state.collect {
                            updateNoteUseCaseImpl.invoke(
                                Note(
                                    id = it.id,
                                    title = event.title,
                                    content = event.content
                                )
                            )
                        }

                    }
                }
            }
        }
    }
}