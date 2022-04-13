package com.globits.nimpe.data.network

import com.globits.nimpe.data.model.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton


interface HealthOrganizationApi {
    @POST("api/health-organization/search")
    suspend fun getHealthOrganization(@Body filter: HealthOrganizationFilter): Page<HealthOrganization>

}