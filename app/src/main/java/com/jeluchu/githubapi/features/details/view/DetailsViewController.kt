package com.jeluchu.githubapi.features.details.view

import com.jeluchu.githubapi.features.details.models.RepositoryDetail
import com.jeluchu.githubapi.core.platform.BasePresenter
import com.jeluchu.githubapi.core.platform.BaseView

object DetailsViewController {

    interface View : BaseView {
        fun showRepository(repositoryDetail: RepositoryDetail)
        fun showReloadButton()
    }

    interface Presenter : BasePresenter<View> {
        fun loadRepository(name: String)
    }

}