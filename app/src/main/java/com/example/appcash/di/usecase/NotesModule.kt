package com.example.appcash.di.usecase

import com.example.appcash.data.repository_interfaces.NoteRepository
import com.example.appcash.domain.notes.implementations.DeleteNoteByIdUseCaseImpl
import com.example.appcash.domain.notes.implementations.GetNoteByIdUseCaseImpl
import com.example.appcash.domain.notes.implementations.GetNotesUseCaseImpl
import com.example.appcash.domain.notes.implementations.UpsertNoteUseCaseImpl
import com.example.appcash.domain.notes.interfaces.DeleteNoteByIdUseCase
import com.example.appcash.domain.notes.interfaces.GetNoteByIdUseCase
import com.example.appcash.domain.notes.interfaces.GetNotesUseCase
import com.example.appcash.domain.notes.interfaces.UpsertNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class NotesModule {
    @Provides
    @ViewModelScoped
    fun provideGetNoteByIdUseCase(repository: NoteRepository): GetNoteByIdUseCase {
        return GetNoteByIdUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertNoteUseCase(repository: NoteRepository): UpsertNoteUseCase {
        return UpsertNoteUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetNotesUseCase(repository: NoteRepository): GetNotesUseCase {
        return GetNotesUseCaseImpl(repository = repository)
    }


    @Provides
    @ViewModelScoped
    fun provideDeleteNoteUseCase(repository: NoteRepository): DeleteNoteByIdUseCase {
        return DeleteNoteByIdUseCaseImpl(repository = repository)
    }
}