package com.marllons.mshttp

import com.marllons.mshttp.factory.OkHttpClientFactory
import com.marllons.mshttp.factory.RetrofitFactory
import com.marllons.mshttp.result.RetrofitResult
import com.marllons.mshttp.result.RetrofitResultImpl
import com.google.gson.Gson
import com.marllons.mshttp.core.configuration.MSNetworkBuildConfig
import com.marllons.mshttp.core.configuration.MSNetworkBuildConfigImpl
import com.marllons.mshttp.core.configuration.initialConfiguration
import com.marllons.mshttp.domain.interceptors.ApiKeyInterceptor
import com.marllons.mshttp.domain.interceptors.HeaderAuthInterceptor
import com.marllons.mshttp.domain.interceptors.HttpClientInterceptor
import com.marllons.mshttp.domain.qualifiers.NetworkQualifiers.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

object MsHttpModules {
    val all = module {

        single<MSNetworkBuildConfig> { MSNetworkBuildConfigImpl() }
        single<RetrofitResult> { RetrofitResultImpl(gson = Gson()) }

        //unprotected
        single(named(UNPROTECTED)) {
            RetrofitFactory.build(url = initialConfiguration().baseUrl, client = get(named(UNPROTECTED)))
        }
        single(named(UNPROTECTED)) { HttpClientInterceptor.createHttpClient(buildConfig = get()) }


        //authenticated
        single(named(AUTHENTICATED)) {
            RetrofitFactory.build(url = initialConfiguration().baseUrl, client = get(named(AUTHENTICATED)))
        }
        single(named(AUTHENTICATED)) { HeaderAuthInterceptor(buildConfig = get(), withAuthorization = true) }

        single(named(AUTHENTICATED)) {
            OkHttpClientFactory.build(interceptor = get(named(AUTHENTICATED)) as HeaderAuthInterceptor, setTimeout = true, buildConfig = get())
        }

        //unauthenticated
        single(named(UNAUTHENTICATED)) {
            RetrofitFactory.build(url = initialConfiguration().baseUrl, client = get(named(UNAUTHENTICATED)))
        }
        single(named(UNAUTHENTICATED)) { HeaderAuthInterceptor(buildConfig = get(), withAuthorization = false) }

        single(named(UNAUTHENTICATED)) {
            OkHttpClientFactory.build(interceptor = get(named(UNAUTHENTICATED)) as HeaderAuthInterceptor, setTimeout = true, buildConfig = get())
        }

        //custom
        single(named(CUSTOM)) {
            RetrofitFactory.build(url = initialConfiguration().baseUrl, client = get(named(CUSTOM)))
        }

        single(named(CUSTOM)) { ApiKeyInterceptor(initialConfiguration().apiKeyKey, initialConfiguration().apiKeyValue) }

        single(named(CUSTOM)) {
            OkHttpClientFactory.build(interceptor = get(named(CUSTOM)) as ApiKeyInterceptor, setTimeout = true, buildConfig = get())
        }
    }
}


