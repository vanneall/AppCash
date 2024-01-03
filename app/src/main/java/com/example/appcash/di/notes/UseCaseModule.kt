package com.example.appcash.di.notes

import com.example.appcash.domain.notes.implementations.GetFolderUseCaseImpl
import com.example.appcash.domain.notes.interfaces.GetFoldersUseCase
import com.example.appcash.data.repository_interfaces.FoldersRepository
import com.example.appcash.domain.notes.implementations.InsertFolderUseCaseImpl
import com.example.appcash.domain.notes.interfaces.InsertFolderUseCase
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
}