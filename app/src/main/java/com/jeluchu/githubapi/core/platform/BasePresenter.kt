package com.jeluchu.githubapi.core.platform

interface BasePresenter<in V : BaseView> {

    fun attachView(view: V)
    fun detachView()

}