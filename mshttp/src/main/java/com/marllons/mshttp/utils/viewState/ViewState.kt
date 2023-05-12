package com.marllons.mshttp.utils.viewState

import androidx.lifecycle.MutableLiveData
import com.marllons.mshttp.domain.model.MSHttpError
import com.marllons.mshttp.utils.viewState.ViewStatus.FAILURE
import com.marllons.mshttp.utils.viewState.ViewStatus.LOADING
import com.marllons.mshttp.utils.viewState.ViewStatus.SUCCESS

class ViewState<T> private constructor(
    val status: ViewStatus = SUCCESS,
    val data: T? = null,
    val failure: MSHttpError? = null
) {

    companion object {
        fun <T> success(data: T): ViewState<T> = ViewState(SUCCESS, data)
        fun <T> loading(): ViewState<T> = ViewState(LOADING)
        fun <T> failure(failure: MSHttpError): ViewState<T> = ViewState(FAILURE, failure = failure)
    }

    fun handleIt(
        onSuccess: (T) -> Unit = {},
        onFailure: (MSHttpError) -> Unit = {},
        isLoading: (Boolean) -> Unit = {}
    ): ViewState<T> {
        when (status) {
            LOADING -> isLoading(true)
            SUCCESS -> {
                this.data?.let { onSuccess(it) }
                isLoading(false)
            }
            FAILURE -> {
                this.failure?.let { onFailure(it) }
                isLoading(false)
            }
        }
        return this
    }

    override fun equals(other: Any?): Boolean {
        return if (other is ViewState<*>) {
            other.data == this.data && other.failure == this.failure && other.status == this.status
        } else {
            false
        }
    }
}

enum class ViewStatus {
    LOADING, SUCCESS, FAILURE
}

fun <T> MutableLiveData<ViewState<T>>.postSuccess(data: T) = postValue(ViewState.success(data))
fun <T> MutableLiveData<ViewState<T>>.postLoading() = postValue(ViewState.loading())
fun <T> MutableLiveData<ViewState<T>>.postFailure(failure: MSHttpError) = postValue(ViewState.failure(failure))



