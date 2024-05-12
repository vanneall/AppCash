package ru.point.data.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.point.data.data.datasource.remote.api.FolderApi
import ru.point.data.data.datasource.remote.api.NoteApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideFolderApi(retrofit: Retrofit): FolderApi {
        return retrofit.create(FolderApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNoteApi(retrofit: Retrofit): NoteApi {
        return retrofit.create(NoteApi::class.java)
    }

}