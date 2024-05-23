package ru.point.data.data.datasource.remote.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.point.data.data.entity.entities.Note

interface NoteApi {

    @GET("note/fetch")
    suspend fun getNotesByFolderId(
        @Query("start") start: Int = 1,
        @Query("end") end: Int = 10,
        @Query("user_id") userId: Int = 2,
        @Query("folder_id") folderId: Int
    ): List<Note>

    @GET("note/fetch")
    suspend fun getNotes(
        @Query("start") start: Int = 1,
        @Query("end") end: Int = 1,
        @Query("user_id") userId:Int = 2
    ): List<Note>

    @POST("note/create")
    suspend fun createNote(@Body note: Note)

}