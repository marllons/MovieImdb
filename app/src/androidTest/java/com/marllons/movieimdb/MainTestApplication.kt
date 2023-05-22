package com.marllons.movieimdb

import android.app.Application
import com.marllons.mshttp.core.configuration.MSNetworkConfiguration

class MainTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setUpConfiguration()
    }

    private fun setUpConfiguration() {
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

}