package com.example.appcash.view.notes.info.components

import com.example.appcash.utils.ArgsKeys
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@AssistedFactory
interface NoteInfoViewModelAssistedFactory {
    fun create(
        @Assisted(ArgsKeys.ID_KEY) noteId: Long?,
        @Assisted(ArgsKeys.CATEGORY_ID_KEY) categoryId: Long?
    ): NoteInfoViewModel
}

@EntryPoint
@InstallIn(ActivityComponent::class)
interface NoteInfoViewModelFactoryProvider {
    fun provideNoteInfoViewModelAssistedFactory(): NoteInfoViewModelAssistedFactory
}

