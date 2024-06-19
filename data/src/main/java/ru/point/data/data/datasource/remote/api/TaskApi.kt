package ru.point.data.data.datasource.remote.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query
import ru.point.data.data.entity.entities.Task
import ru.point.data.data.entity.entities.TaskWithTask


interface TaskApi {
    @GET("task/fetch")
    fun getTasksByFolderId(
        @Query("start") start: Int = 1,
        @Query("end") end: Int = 100,
        @Query("user_id") userId: Int = 2,
        @Query("folder_id") folderId: Long
    ): Call<List<TaskWithTask>>

    @GET("/task/fetch")
    fun getAllTasks(
        @Query("start") start: Int = 1,
        @Query("end") end: Int = 100,
        @Query("user_id") userId: Int = 2,
    ): Call<List<TaskWithTask>>

    @POST("task/create")
    fun createTask(@Body dto: TaskDto): Call<Any>

    @PATCH("task/update")
    fun updateCheckedTask(@Body dto: TaskWithTask): Call<Any>

    @PATCH("task/update")
    fun updateCheckedTask(@Body dto: Task): Call<Any>

    @DELETE("task/delete")
    fun deleteTaskById(@Query("to_del") toDel: Long): Call<Any>
}