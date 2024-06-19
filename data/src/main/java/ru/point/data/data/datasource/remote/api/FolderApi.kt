package ru.point.data.data.datasource.remote.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.point.data.data.entity.entities.Category

interface FolderApi {

    @GET("folder/fetch")
    suspend fun getFolders(
        @Query("user") userId: Int = 2,
        @Query("start") start: Int = 1,
        @Query("end") end: Int = 10,
        @Query("folder_type") folderType: String
    ): List<Category>

    @GET("folder/fetch")
    suspend fun getFolders(
        @Query("user") userId: Int = 2,
        @Query("start") start: Int = 1,
        @Query("end") end: Int = 10,
    ): List<Category>

    @GET("folder/fetch-by-id")
    suspend fun getFolderById(
        @Query("folder_id") folderId: Long,
        @Query("user_id") userId: Int = 2,
    ): List<Category>

    @POST("folder/create")
    suspend fun createFolder( @Body category: Category, @Query("user") userId: Int = 2)
}