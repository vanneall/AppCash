package com.example.appcash.di.notes

import com.example.appcash.domain.notes.implementations.GetFolderUseCaseImpl
import com.example.appcash.domain.notes.interfaces.GetFoldersUseCase
import com.example.appcash.data.repository_interfaces.FoldersRepository
import com.example.appcash.data.repository_interfaces.NoteRepository
import com.example.appcash.data.repository_interfaces.NoteToFolderLinkRepository
import com.example.appcash.domain.notes.implementations.GetFolderNameByIdUseCaseImpl
import com.example.appcash.domain.notes.implementations.GetNoteByIdUseCaseImpl
import com.example.appcash.domain.notes.implementations.GetNotesUseCaseImpl
import com.example.appcash.domain.notes.implementations.InsertFolderUseCaseImpl
import com.example.appcash.domain.notes.implementations.InsertNoteUseCaseImpl
import com.example.appcash.domain.notes.implementations.UpdateNoteUseCaseImpl
import com.example.appcash.domain.notes.interfaces.GetFolderNameByIdUseCase
import com.example.appcash.domain.notes.interfaces.GetNoteByIdUseCase
import com.example.appcash.domain.notes.interfaces.GetNotesUseCase
import com.example.appcash.domain.notes.interfaces.InsertFolderUseCase
import com.example.appcash.domain.notes.interfaces.InsertNoteUseCase
import com.example.appcash.domain.notes.interfaces.UpdateNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideGetFoldersUseCase(repository: FoldersRepository): GetFoldersUseCase {
        return GetFolderUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertFolderUseCase(repository: FoldersRepository): InsertFolderUseCase {
        return InsertFolderUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetFolderNameByIdUseCase(repository: FoldersRepository): GetFolderNameByIdUseCase {
        return GetFolderNameByIdUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetNotesUseCase(repository: NoteToFolderLinkRepository): GetNotesUseCase {
        return GetNotesUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetNoteByIdUseCase(repository: NoteRepository): GetNoteByIdUseCase {
        return GetNoteByIdUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertNoteUseCase(repository: NoteRepository): InsertNoteUseCase {
        return InsertNoteUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideUpdateNoteUseCase(repository: NoteRepository): UpdateNoteUseCase {
        return UpdateNoteUseCaseImpl(repository = repository)
    }
}