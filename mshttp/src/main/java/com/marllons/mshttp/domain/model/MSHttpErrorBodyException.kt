package com.marllons.mshttp.domain.model

data class MSHttpErrorBodyException(
    val code: Int,
    val jsonError: String? = null
): Exception()
