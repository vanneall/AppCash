package com.example.appcash.di.repository

import com.example.appcash.data.databases.FoldersDatabase
import com.example.appcash.data.repository_implementations.FoldersRepositoryImpl
import com.example.appcash.data.repository_interfaces.FoldersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideFoldersRepository(database: FoldersDatabase): FoldersRepository {
        return FoldersRepositoryImpl(database = database)
    }
}