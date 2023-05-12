package com.marllons.mshttp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class MSHttpError(
    val code: Int = -1,
    val title: String = "",
    val message: String = "",
    val buttonFirst: String = "Ok",
    val buttonSecond: String = "",
    var cause: Throwable? = null
) : Parcelable {

    fun isValid(): Boolean = code != -1 && title.isNotEmpty() && message.isNotEmpty()

}