package com.marllons.mshttp.domain.interceptors

import com.marllons.mshttp.core.configuration.MSNetworkBuildConfig
import com.marllons.mshttp.domain.interceptors.DefaultHeaderConstants.HEADER_AUTHORIZATION_TOKEN
import com.marllons.mshttp.domain.interceptors.DefaultHeaderConstants.HEADER_DEVICE
import com.marllons.mshttp.domain.interceptors.DefaultHeaderConstants.HEADER_MIN_VERSION_KEY
import com.marllons.mshttp.domain.interceptors.DefaultHeaderConstants.HEADER_MODEL
import com.marllons.mshttp.domain.interceptors.DefaultHeaderConstants.HEADER_OPERATING_SYSTEM
import com.marllons.mshttp.domain.interceptors.DefaultHeaderConstants.HEADER_OS
import com.marllons.mshttp.domain.interceptors.DefaultHeaderConstants.HEADER_VENDOR
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderAuthInterceptor(
    private val buildConfig: MSNetworkBuildConfig? = null,
    private val withAuthorization: Boolean = true,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder().apply { buildHeader() }.build()
        return chain.proceed(newRequest)
    }

    private fun Request.Builder.buildHeader() {
        val accessToken = "getHereAccessToken"
        if (withAuthorization) {
            accessToken.let { addHeader(HEADER_AUTHORIZATION_TOKEN, accessToken) }
        }
        addHeader(HEADER_MODEL, buildConfig?.getDeviceModel() ?: "")
        addHeader(HEADER_OS, buildConfig?.getVersionSDKString() ?: "")
        addHeader(HEADER_VENDOR, buildConfig?.getManufacturer() ?: "")
        addHeader(HEADER_OPERATING_SYSTEM, buildConfig?.getOperatingSystemConstant() ?: "")
        addHeader(HEADER_DEVICE, buildConfig?.getDeviceModel() ?: "")
        addHeader(HEADER_MIN_VERSION_KEY, buildConfig?.getMinVersionKey() ?: "")
    }
}
