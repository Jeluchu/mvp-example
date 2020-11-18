package com.jeluchu.githubapi.core.platform

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

abstract class BaseActivity<in V : BaseView, T : BasePresenter<V>>
    : AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this as V)
    }

    override fun getContext(): Context = this

    protected abstract var mPresenter: T

    override fun showError(error: String?) =
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()

    override fun showError(stringResId: Int) =
            Toast.makeText(this, stringResId, Toast.LENGTH_LONG).show()

    override fun showMessage(srtResId: Int) =
            Toast.makeText(this, srtResId, Toast.LENGTH_LONG).show()

    override fun showMessage(message: String) =
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}