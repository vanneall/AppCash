package ru.point.data.data.datasource.remote.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.point.data.data.entity.dto.FinanceDto
import ru.point.data.data.entity.subset.FinanceSubset
import ru.point.data.data.entity.subset.FinanceSubsetForRemote

interface FinanceApi {

    @GET("finance/fetch")
    fun getFinances(
        @Query("user_id") userId: Long = 2,
        @Query("start") start: Int = 1,
        @Query("end") end: Int = 100
    ): Call<List<FinanceSubset>>

    @POST("finance/create")
    fun createFinance(@Body finance: FinanceDto): Call<Any>

    @GET("finance/fetch-income")
    fun getIncome(
        @Query("yearMonth") yearMonth: String,
        @Query("user_id") userId: Long = 2,
        @Query("start") start: Int = 1,
        @Query("end") end: Int = 100
    ): Call<List<FinanceSubsetForRemote>>

    @GET("finance/fetch-expense")
    fun getExpense(
        @Query("yearMonth") yearMonth: String,
        @Query("user_id") userId: Long = 2,
        @Query("start") start: Int = 1,
        @Query("end") end: Int = 100
    ): Call<List<FinanceSubsetForRemote>>

}