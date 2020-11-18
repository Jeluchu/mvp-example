package com.jeluchu.githubapi.features.details.logic

import com.jeluchu.githubapi.core.exceptions.ErrorHandler
import com.jeluchu.githubapi.core.api.ApiManager
import com.jeluchu.githubapi.core.platform.BasePresenterImpl
import com.jeluchu.githubapi.features.details.view.DetailsViewController

class DetailsPresenter : BasePresenterImpl<DetailsViewController.View>(),
        DetailsViewController.Presenter {

    companion object {
        private const val ORGANIZATION_NAME = "Aruppi"
    }

    override fun loadRepository(name: String) {
        ApiManager.loadRepository(ORGANIZATION_NAME, name)
                .subscribe(
                        { mView?.showRepository(it) },
                        ErrorHandler(mView, true)
                        { _, _, _ -> mView?.showReloadButton() }
                )
    }
}