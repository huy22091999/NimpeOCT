package com.globits.nimpe.data.network

import android.location.Location
import com.globits.nimpe.data.model.*
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.time.Year
import javax.inject.Singleton


interface ReDengueLocationApi {
    @GET("public/app/getDegueLocation")
    fun getAllLocation():Call<ResponseBody>

    @POST("api/dengue-location/searchByDto")
    fun getDengueLocation(@Body filter: DengueLocationFilter): Call<Page<DengueLocation>>
    @GET("api/patientInformation/listPatient")
    fun getListPatient(@Query("month") month:Int,@Query("year")year: Int): Call<List<Patient>>
    @GET("api/dengue-location-item/listDengueLocationItem")
    fun getListDengueLocation(@Query("month") month:Int,@Query("year")year: Int): Call<List<Vector>>
    @POST("public/app")
    fun saveFeedback(@Body feedback: Feedback) : Observable<Feedback>
}