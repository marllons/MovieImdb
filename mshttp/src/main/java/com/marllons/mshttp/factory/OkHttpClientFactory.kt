package com.marllons.mshttp.factory

import com.marllons.mshttp.DEFAULT_TIMEOUT_CONNECT
import com.marllons.mshttp.TIMEOUT_CONNECT
import com.marllons.mshttp.TIMEOUT_READ
import com.marllons.mshttp.core.configuration.MSNetworkBuildConfig
import com.marllons.mshttp.domain.interceptors.ApiKeyInterceptor
import com.marllons.mshttp.domain.interceptors.HeaderAuthInterceptor
import java.util.concurrent.TimeUnit
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkHttpClientFactory {

    fun build(
        interceptor: Interceptor?,
        buildConfig: MSNetworkBuildConfig,
        setTimeout: Boolean,
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .dispatcher(Dispatcher().apply { maxRequests = 1 })
            .readTimeout(TIMEOUT_READ.toLong(), TimeUnit.MINUTES)
            .connectTimeout(if (setTimeout) DEFAULT_TIMEOUT_CONNECT.toLong() else TIMEOUT_CONNECT.toLong(), TimeUnit.SECONDS)

        interceptor?.let {
            clientBuilder.addInterceptor(interceptor)
        }

        if (buildConfig.isDebug()) {
            clientBuilder.addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        }

        return clientBuilder.build()
    }
}