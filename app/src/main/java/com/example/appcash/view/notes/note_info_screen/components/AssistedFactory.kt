package com.example.appcash.view.notes.note_info_screen.components

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@AssistedFactory
interface NoteInfoViewModelFactory {
    fun create(
        @Assisted mode: NoteOpenOpenMode,
        @Assisted("id") id: Long,
        @Assisted("folderId") folderId: Long
    ): NoteInfoViewModel
}

@EntryPoint
@InstallIn(ActivityComponent::class)
interface NoteInfoViewModelFactoryProvider {
    fun provideNoteInfoViewModelFactory(): NoteInfoViewModelFactory
}

