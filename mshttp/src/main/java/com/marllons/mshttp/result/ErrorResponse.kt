package com.marllons.mshttp.result

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ErrorResponse(
    @SerializedName("Response")
    val response: String? = null,
    @SerializedName("Error")
    val error: String? = null
) {

    fun isValid(): Boolean = error?.isNotEmpty() == true && response?.isNotEmpty() == true

}
