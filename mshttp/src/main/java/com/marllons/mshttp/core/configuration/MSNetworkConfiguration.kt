package com.marllons.mshttp.core.configuration

@DslMarker
annotation class ConfigurationDsl

object MSNetworkConfiguration {
    lateinit var config: Config
        private set

    fun setup(lambda: ConfigBuilder.() -> Unit) {
        config = ConfigBuilder().apply(lambda).build()
    }
}

fun initialConfiguration(): Config = MSNetworkConfiguration.config

@ConfigurationDsl
class ConfigBuilder {
    private var debug = false
    private var versionCode = -1
    private var versionName = ""
    private var appId = ""
    private var flavor = ""
    private var baseUrl = ""
    private var buildType = ""
    private var isEmulator = false
    private var minVersionKey = ""
    private var apiKeyKey = ""
    private var apiKeyValue = ""

    fun debug(lambda: () -> Boolean) {
        this.debug = lambda()
    }

    fun versionCode(lambda: () -> Int) {
        this.versionCode = lambda()
    }

    fun versionName(lambda: () -> String) {
        this.versionName = lambda()
    }

    fun appId(lambda: () -> String) {
        this.appId = lambda()
    }

    fun flavor(lambda: () -> String) {
        this.flavor = lambda()
    }

    fun baseUrl(lambda: () -> String) {
        this.baseUrl = lambda()
    }

    fun buildType(lambda: () -> String) {
        this.buildType = lambda()
    }

    fun minVersionKey(lambda: () -> String) {
        this.minVersionKey = lambda()
    }

    fun apiKeyKey(lambda: () -> String) {
        this.apiKeyKey = lambda()
    }

    fun apiKeyValue(lambda: () -> String) {
        this.apiKeyValue = lambda()
    }

    fun build() = Config(
        debug, versionCode, versionName, appId, flavor, baseUrl, buildType, isEmulator, minVersionKey, apiKeyKey, apiKeyValue
    )
}

data class Config(
    val debug: Boolean = false,
    val versionCode: Int = -1,
    val versionName: String = "",
    val appId: String = "",
    val flavor: String = "",
    val baseUrl: String = "",
    val buildType: String = "",
    val isEmulator: Boolean = false,
    val minVersionKey: String = "",
    val apiKeyValue: String = "",
    val apiKeyKey: String = "",
)