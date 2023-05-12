package com.marllons.mshttp.core.configuration

import okhttp3.Interceptor

interface MSNetworkBuildConfig {
    fun isDebug(): Boolean

    fun getVersionSDKString(): String

    fun getDeviceModel(): String

    fun getManufacturer(): String

    fun getAppVersion(): String

    fun getAppVersionName(): String

    fun getAppId(): String

    fun getTransactionId(): String

    fun getOperatingSystemConstant(): String

    fun getBuildType(): String

    fun getFlavor(): String

    fun getMinVersionKey(): String

}