package com.jeluchu.githubapi.features.listing.view

import com.jeluchu.githubapi.features.listing.models.Repository
import com.jeluchu.githubapi.core.platform.BasePresenter
import com.jeluchu.githubapi.core.platform.BaseView

object ListViewController {

    interface View : BaseView {
        fun showOrganizations(repositories: MutableList<Repository>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadRepositories()
    }

}
