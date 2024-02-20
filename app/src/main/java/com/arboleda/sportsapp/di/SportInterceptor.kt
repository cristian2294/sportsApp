package com.arboleda.sportsapp.di

import okhttp3.Interceptor
import okhttp3.Response

class SportInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val request = originalRequest.newBuilder()
            .header(NAME_HEADER_KEY, API_KEY)
            .build()

        return chain.proceed(request)
    }
}

private const val API_KEY = "7632b970f4aac61c735376620e555092"
private const val NAME_HEADER_KEY = "x-apisports-key"
