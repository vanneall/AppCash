package ru.point.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.point.data.data.factory.NoteFactory
import ru.point.data.data.datasource.repository.interfaces.NotesRepository
import ru.point.domain.notes.implementations.DeleteNoteByIdUseCaseImpl
import ru.point.domain.notes.implementations.GetAllNotesCountUseCaseImpl
import ru.point.domain.notes.implementations.GetNoteByIdUseCaseImpl
import ru.point.domain.notes.implementations.GetNotesUseCaseImpl
import ru.point.domain.notes.implementations.UpsertNoteUseCaseImpl
import ru.point.domain.notes.interfaces.DeleteNoteByIdUseCase
import ru.point.domain.notes.interfaces.GetAllNotesCountUseCase
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
    fun provideInsertNoteUseCase(noteFactory: NoteFactory, repository: NotesRepository): UpsertNoteUseCase {
        return UpsertNoteUseCaseImpl(noteFactory = noteFactory, repository = repository)
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

    @Provides
    @ViewModelScoped
    fun provideGetNotesCountUseCase(repository: NotesRepository): GetAllNotesCountUseCase {
        return GetAllNotesCountUseCaseImpl(repository = repository)
    }
}