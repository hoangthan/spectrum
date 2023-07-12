package com.spectrum.libraries.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    private val apiKey = "api_key"
    private val keyValue = "0e7274f05c36db12cbe71d9ab0393d47"
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val modifiedUrl = originalRequest.url.newBuilder()
            .addQueryParameter(apiKey, keyValue)
            .build()

        val modifiedRequest = originalRequest.newBuilder()
            .url(modifiedUrl)
            .build()

        return chain.proceed(modifiedRequest)
    }
}
