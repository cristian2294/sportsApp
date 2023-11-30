package com.arboleda.sportsapp.di

import okhttp3.Interceptor
import okhttp3.Response

class SportInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val request = originalRequest.newBuilder()
            .header("x-apisports-key", API_KEY)
            .build()

        return chain.proceed(request)
    }
}

const val API_KEY = "7632b970f4aac61c735376620e555092"
