package ru.point.data.data.datasource.remote.api

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NoteApi {

    @GET("note/fetch")
    fun getNotesByFolderId(
        @Query("start") start: Int = 1,
        @Query("end") end: Int = 1,
        @Query("user_id") userId:Int = 1,
        @Query("folder_id") folderId: Int
    )

    @GET("note/fetch")
    fun getNotes(
        @Query("start") start: Int = 1,
        @Query("end") end: Int = 1,
        @Query("user_id") userId:Int = 1
    )

    @POST("note/create")
    fun createNote()

}