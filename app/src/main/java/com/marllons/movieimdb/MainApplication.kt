package com.marllons.movieimdb

import android.app.Application
import com.marllons.movieimdb.di.movieImdbModules
import com.marllons.mshttp.MsHttpModules
import com.marllons.mshttp.core.configuration.MSNetworkConfiguration
import okhttp3.Interceptor
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject

open class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeConfig()
        initializeKoin()
    }

    private fun initializeConfig() {
        MSNetworkConfiguration.setup {
            debug { BuildConfig.DEBUG }
            versionCode { BuildConfig.VERSION_CODE }
            versionName { BuildConfig.VERSION_NAME }
            minVersionKey { BuildConfig.VERSION_NAME }
            baseUrl { Constants.BASE_URL }
            apiKeyKey { Constants.API_KEY_KEY }
            apiKeyValue { Constants.API_KEY_VALUE }
            appId { BuildConfig.APPLICATION_ID }
            buildType { BuildConfig.BUILD_TYPE }
        }
    }

    private fun initializeKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(listOf(MsHttpModules.all, movieImdbModules))
        }
    }

}