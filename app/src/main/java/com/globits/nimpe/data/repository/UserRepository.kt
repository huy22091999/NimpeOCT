package com.globits.nimpe.data.repository

import com.globits.nimpe.data.model.User
import com.globits.nimpe.data.network.UserApi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    val api: UserApi
) {
    fun getUser(): Observable<List<User>> = api.getUser().subscribeOn(Schedulers.io())
    fun getString(): String = "test part"
}