package com.jeluchu.githubapi.core.exceptions

import com.jeluchu.githubapi.core.platform.BaseView
import com.jeluchu.githubapi.R
import retrofit2.adapter.rxjava.HttpException
import rx.functions.Action1
import java.lang.ref.WeakReference
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorHandler(view: BaseView? = null,
                   private val mShowError: Boolean = false,
                   val onFailure: (Throwable, ErrorBody?, Boolean) -> Unit)
: Action1<Throwable> {

    private val mViewReference: WeakReference<BaseView> = WeakReference<BaseView>(view)

    override fun call(throwable: Throwable) {
        var isNetwork = false
        var errorBody: ErrorBody? = null
        if (isNetworkError(throwable)) {
            isNetwork = true
            showMessage()
        } else if (throwable is HttpException) {
            errorBody = ErrorBody.parseError(throwable.response())
            if (errorBody != null) handleError(errorBody)
        }
        onFailure(throwable, errorBody, isNetwork)
    }

    private fun isNetworkError(throwable: Throwable): Boolean =
            throwable is SocketException ||
            throwable is UnknownHostException ||
            throwable is SocketTimeoutException

    private fun handleError(errorBody: ErrorBody) {
        if (errorBody.code != ErrorBody.UNKNOWN_ERROR) showErrorIfRequired()
    }

    private fun showErrorIfRequired() {
        if (mShowError) mViewReference.get()?.showError(R.string.server_error)
    }

    private fun showMessage() = mViewReference.get()?.showMessage(R.string.server_error)

}