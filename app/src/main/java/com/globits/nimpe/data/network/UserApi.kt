package com.globits.nimpe.data.network

import com.globits.nimpe.data.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import javax.inject.Singleton


interface UserApi {
    @GET("/")
    fun getUser(): Observable<List<User>>

}