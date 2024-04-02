package ru.point.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.point.data.data.repository_interfaces.NotesRepository
import ru.point.domain.notes.implementations.DeleteNoteByIdUseCaseImpl
import ru.point.domain.notes.implementations.GetNoteByIdUseCaseImpl
import ru.point.domain.notes.implementations.GetNotesUseCaseImpl
import ru.point.domain.notes.implementations.UpsertNoteUseCaseImpl
import ru.point.domain.notes.interfaces.DeleteNoteByIdUseCase
import ru.point.domain.notes.interfaces.GetNoteByIdUseCase
import ru.point.domain.notes.interfaces.GetNotesUseCase
import ru.point.domain.notes.interfaces.UpsertNoteUseCase

@Module
@InstallIn(ViewModelComponent::class)
class NotesModule {
    @Provides
    @ViewModelScoped
    fun provideGetNoteByIdUseCase(repository: NotesRepository): GetNoteByIdUseCase {
        return GetNoteByIdUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertNoteUseCase(repository: NotesRepository): UpsertNoteUseCase {
        return UpsertNoteUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetNotesUseCase(repository: NotesRepository): GetNotesUseCase {
        return GetNotesUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideDeleteNoteUseCase(repository: NotesRepository): DeleteNoteByIdUseCase {
        return DeleteNoteByIdUseCaseImpl(repository = repository)
    }
}