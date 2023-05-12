package com.marllons.mshttp.core.configuration

import android.os.Build
import java.util.*
import okhttp3.Interceptor

private const val OPERATING_SYSTEM_NAME = "Android"

class MSNetworkBuildConfigImpl: MSNetworkBuildConfig {

    override fun isDebug() = initialConfiguration().debug

    override fun getVersionSDKString() = Build.VERSION.SDK_INT.toString()

    override fun getDeviceModel(): String = Build.MODEL

    override fun getManufacturer(): String = Build.MANUFACTURER

    override fun getAppVersion() = initialConfiguration().versionCode.toString()

    override fun getAppVersionName(): String {
        return initialConfiguration().versionName
    }

    override fun getAppId() = initialConfiguration().appId

    override fun getTransactionId() = UUID.randomUUID().toString()

    override fun getOperatingSystemConstant() = OPERATING_SYSTEM_NAME

    override fun getBuildType(): String = initialConfiguration().buildType

    override fun getFlavor(): String = initialConfiguration().flavor

    override fun getMinVersionKey(): String {
        return initialConfiguration().minVersionKey
    }
}