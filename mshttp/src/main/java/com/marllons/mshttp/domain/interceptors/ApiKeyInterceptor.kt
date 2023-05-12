package com.marllons.mshttp.domain.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(
    private val apiKeyKey: String,
    private val apiKeyValue: String,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
        val url = chain.request().url.newBuilder()
            .addQueryParameter(apiKeyValue, apiKeyKey).build()
        newRequest.url(url)
        return chain.proceed(newRequest.build())
    }
}
