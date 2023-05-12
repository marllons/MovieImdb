package com.marllons.mshttp.result

import com.marllons.mshttp.domain.model.MSHttpError

const val GENERIC_ERROR_TITLE = "Erro"
const val GENERIC_ERROR_MESSAGE = "Erro desconhecido"

fun ErrorResponse.toError() = MSHttpError(
    title = GENERIC_ERROR_TITLE,
    message = this.error?.ifEmpty { GENERIC_ERROR_MESSAGE } ?: GENERIC_ERROR_MESSAGE
)
