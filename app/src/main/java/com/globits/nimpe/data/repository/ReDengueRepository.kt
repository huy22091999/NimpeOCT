package com.globits.nimpe.data.repository

import com.globits.nimpe.data.model.Feedback
import com.globits.nimpe.data.model.User
import com.globits.nimpe.data.network.ReDengueLocationApi
import com.globits.nimpe.data.network.UserApi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class ReDengueRepository @Inject constructor(
    val api: ReDengueLocationApi
) {
    fun saveFeedback(feedback: Feedback):Observable<Feedback> = api.saveFeedback(feedback).subscribeOn(Schedulers.io())
}