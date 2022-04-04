package com.globits.nimpe.data.repository

import com.globits.nimpe.data.model.TokenResponse
import com.globits.nimpe.data.model.User
import com.globits.nimpe.data.model.UserCredentials
import com.globits.nimpe.data.network.AuthApi
import com.globits.nimpe.ui.security.UserPreferences
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    val api: AuthApi,
    val preferences: UserPreferences
) {
    fun login(username: String, password: String): Observable<TokenResponse> = api.oauth(
        UserCredentials(
            AuthApi.CLIENT_ID,
            AuthApi.CLIENT_SECRET,
            username,
            password,
            null,
            AuthApi.GRANT_TYPE_PASSWORD
        )
    ).subscribeOn(Schedulers.io())
    suspend fun saveAccessTokens(tokens: TokenResponse) {
        if (tokens.accessToken == null || tokens.refreshToken == null) {
            return
        }
        preferences.saveAccessTokens(tokens.accessToken, tokens.refreshToken)
    }

}