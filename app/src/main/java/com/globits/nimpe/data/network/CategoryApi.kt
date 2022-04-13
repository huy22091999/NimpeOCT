package com.globits.nimpe.data.network

import com.globits.nimpe.data.model.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton


interface CategoryApi {
    @POST("api/nimpe-category/searchByPage")
    fun getCategory(@Body filter: CategoryFilter): Observable<Page<Category>>
    @POST("api/nimpe-article/searchByDto")
    suspend fun getNews(@Body filter: NewsFilter): Page<News>

}