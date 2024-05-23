package ru.point.data.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import ru.point.data.data.datasource.remote.api.FolderApi
import ru.point.data.data.datasource.remote.api.NoteApi
import ru.point.data.data.datasource.settings.SettingsStore

@Module
@InstallIn(ViewModelComponent::class)
class ApiModule {

    @Provides
    @ViewModelScoped
    fun provideFolderApi(retrofit: Retrofit): FolderApi {
        return retrofit.create(FolderApi::class.java)
    }

    @Provides
    @ViewModelScoped
    fun provideNoteApi(retrofit: Retrofit): NoteApi {
        return retrofit.create(NoteApi::class.java)
    }

}