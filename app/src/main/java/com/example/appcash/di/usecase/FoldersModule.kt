package com.example.appcash.di.usecase

import com.example.appcash.data.repository_interfaces.FoldersRepository
import com.example.appcash.data.repository_interfaces.NoteToFolderLinkRepository
import com.example.appcash.domain.notes.implementations.DeleteFolderByIdImpl
import com.example.appcash.domain.notes.implementations.GetFolderByTypeUseCaseImpl
import com.example.appcash.domain.notes.implementations.GetFolderNameByIdUseCaseImpl
import com.example.appcash.domain.notes.implementations.GetNotesByFolderIdUseCaseImpl
import com.example.appcash.domain.notes.implementations.InsertFolderUseCaseImpl
import com.example.appcash.domain.notes.implementations.UpdateFolderUseCaseImpl
import com.example.appcash.domain.notes.interfaces.DeleteByIdFolderUseCase
import com.example.appcash.domain.notes.interfaces.GetFolderNameByIdUseCase
import com.example.appcash.domain.notes.interfaces.GetFoldersByTypeUseCase
import com.example.appcash.domain.notes.interfaces.GetNotesByFolderIdUseCase
import com.example.appcash.domain.notes.interfaces.InsertFolderUseCase
import com.example.appcash.domain.notes.interfaces.UpdateFolderUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class FoldersModule {
    @Provides
    @ViewModelScoped
    fun provideDeleteFolderByIdUseCase(repository: FoldersRepository): DeleteByIdFolderUseCase {
        return DeleteFolderByIdImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideUpdateFolderUseCase(repository: FoldersRepository): UpdateFolderUseCase {
        return UpdateFolderUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetFoldersUseCase(repository: FoldersRepository): GetFoldersByTypeUseCase {
        return GetFolderByTypeUseCaseImpl(repository = repository)
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
    fun provideGetNotesByFolderIdUseCase(repository: NoteToFolderLinkRepository): GetNotesByFolderIdUseCase {
        return GetNotesByFolderIdUseCaseImpl(repository = repository)
    }
}