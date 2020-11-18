package com.jeluchu.githubapi.features.listing.logic

import com.jeluchu.githubapi.core.exceptions.ErrorHandler
import com.jeluchu.githubapi.core.api.ApiManager
import com.jeluchu.githubapi.core.platform.BasePresenterImpl
import com.jeluchu.githubapi.features.listing.view.ListViewController

class ListPresenter : BasePresenterImpl<ListViewController.View>(), ListViewController.Presenter {

    companion object {
        private const val ORGANIZATION_NAME = "Aruppi"
        private const val REPOS_TYPE = "public"
    }

    override fun loadRepositories() {
        ApiManager.loadOrganizationRepos(ORGANIZATION_NAME, REPOS_TYPE)
                .doOnError { mView?.showMessage(it.toString()) }
                .subscribe({ mView?.showOrganizations(it) },
                        ErrorHandler(mView, true)
                        { throwable, _, _ -> mView?.showError(throwable.message) }
                )
    }
}