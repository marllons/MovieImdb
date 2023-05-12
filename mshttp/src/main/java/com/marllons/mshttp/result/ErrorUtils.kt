package com.marllons.mshttp.result

import com.marllons.mshttp.CODE_ERROR_DEFAULT
import com.marllons.mshttp.MSG_ERROR_CONECTION
import com.marllons.mshttp.MSG_ERROR_DEFAULT
import com.marllons.mshttp.TEXT_BUTTON_DEFAULT
import com.marllons.mshttp.TITLE_MSG_ERROR_CONECTION
import com.marllons.mshttp.TITLE_MSG_ERROR_DEFAULT
import com.marllons.mshttp.domain.model.MSHttpError

val GENERIC_ERROR = MSHttpError(
    code = CODE_ERROR_DEFAULT,
    title = TITLE_MSG_ERROR_DEFAULT,
    message = MSG_ERROR_DEFAULT,
    buttonFirst = "",
    buttonSecond = TEXT_BUTTON_DEFAULT
)

val CONNECTION_ERROR = MSHttpError(
    code = CODE_ERROR_DEFAULT,
    title = TITLE_MSG_ERROR_CONECTION,
    message = MSG_ERROR_CONECTION,
    buttonFirst = "",
    buttonSecond = TEXT_BUTTON_DEFAULT
)
