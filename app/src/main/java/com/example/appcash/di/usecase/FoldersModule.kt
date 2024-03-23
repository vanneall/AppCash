package com.example.appcash.di.usecase

import com.example.appcash.data.repository_interfaces.CategoriesRepository
import com.example.appcash.domain.notes.implementations.DeleteFolderByIdImpl
import com.example.appcash.domain.notes.implementations.GetFolderByTypeUseCaseImpl
import com.example.appcash.domain.notes.implementations.GetCategoryNameByIdUseCaseImpl
import com.example.appcash.domain.notes.implementations.InsertFolderUseCaseImpl
import com.example.appcash.domain.notes.implementations.UpdateCategoryUseCaseImpl
import com.example.appcash.domain.notes.interfaces.DeleteByIdFolderUseCase
import com.example.appcash.domain.notes.interfaces.GetCategoryNameByIdUseCase
import com.example.appcash.domain.notes.interfaces.GetCategoryByTypeUseCase
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
    fun provideDeleteFolderByIdUseCase(repository: CategoriesRepository): DeleteByIdFolderUseCase {
        return DeleteFolderByIdImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideUpdateFolderUseCase(repository: CategoriesRepository): UpdateFolderUseCase {
        return UpdateCategoryUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetFoldersUseCase(repository: CategoriesRepository): GetCategoryByTypeUseCase {
        return GetFolderByTypeUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertFolderUseCase(repository: CategoriesRepository): InsertFolderUseCase {
        return InsertFolderUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetFolderNameByIdUseCase(repository: CategoriesRepository): GetCategoryNameByIdUseCase {
        return GetCategoryNameByIdUseCaseImpl(repository = repository)
    }
}