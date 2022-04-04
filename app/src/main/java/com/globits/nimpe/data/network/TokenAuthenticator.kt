package com.globits.nimpe.data.network

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    accessToken: String
) : Authenticator {

    private val mAccessToken = accessToken

    override fun authenticate(route: Route?, response: Response): Request {
        return response.request.newBuilder()
            .header("Authorization", "Bearer $mAccessToken")
            .build()
    }
}
