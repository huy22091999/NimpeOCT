package com.globits.nimpe.data.network

import com.globits.nimpe.data.model.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton


interface ReDengueLocationApi {
    @POST("api/dengue-location/searchByDto")
    fun getDengueLocation(@Body filter: DengueLocationFilter): Call<Page<DengueLocation>>

}