package com.marllons.mshttp.result

import retrofit2.Response

interface RetrofitResult {
    suspend fun <T> getResult(request: suspend () -> Response<T>): Result<T>
}