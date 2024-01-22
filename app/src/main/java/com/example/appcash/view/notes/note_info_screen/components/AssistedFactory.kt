package com.example.appcash.view.notes.note_info_screen.components

import com.example.appcash.utils.ArgsKeys
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@AssistedFactory
interface NoteInfoViewModelFactory {
    fun create(
        @Assisted openMode: NoteOpenMode,
        @Assisted(ArgsKeys.ID_KEY) noteId: Long,
        @Assisted(ArgsKeys.FOLDER_ID_KEY) folderId: Long
    ): NoteInfoViewModel
}

@EntryPoint
@InstallIn(ActivityComponent::class)
interface NoteInfoViewModelFactoryProvider {
    fun provideNoteInfoViewModelFactory(): NoteInfoViewModelFactory
}

