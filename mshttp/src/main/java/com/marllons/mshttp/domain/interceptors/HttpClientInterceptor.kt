package com.marllons.mshttp.domain.interceptors

import com.marllons.mshttp.core.configuration.MSNetworkBuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object HttpClientInterceptor {

    fun createHttpClient(buildConfig: MSNetworkBuildConfig): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        if (buildConfig.isDebug()) {
            clientBuilder.addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        }
        return clientBuilder.build()
    }
}


