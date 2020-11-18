package com.jeluchu.githubapi.features.details.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.jeluchu.githubapi.core.extensions.formatDate
import com.jeluchu.githubapi.features.details.models.RepositoryDetail
import com.jeluchu.githubapi.core.platform.BaseActivity
import com.jeluchu.githubapi.features.details.logic.DetailsPresenter
import com.jeluchu.githubapi.R
import kotlinx.android.synthetic.main.activity_repository.*

class DetailActivity : BaseActivity<DetailsViewController.View,
        DetailsViewController.Presenter>(),
        DetailsViewController.View {

    override var mPresenter: DetailsViewController.Presenter = DetailsPresenter()

    companion object {
        private const val NAME = "name"

        fun newIntent(context: Context, name: String): Intent =
                Intent(context, DetailActivity::class.java).apply {
                    putExtra(NAME, name)
                }
    }


    override fun showRepository(repositoryDetail: RepositoryDetail) {
        layoutContent.visibility = View.VISIBLE
        pbDetails.visibility = View.GONE
        with(repositoryDetail) {
            toolbar.title = name
            textCreatedDate.text = "Created at : ${created_at.formatDate()}"
            textUpdatedDate.text = "Updated at : ${updated_at.formatDate()}"
            textDescription.text = description
            textForks.text = forks_count
            textStars.text = stargazers_count
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        layoutContent.visibility = View.GONE
        pbDetails.visibility = View.VISIBLE

        intent.getStringExtra(NAME)?.let { mPresenter.loadRepository(it) }

        btnReload.setOnClickListener {
            pbDetails.visibility = View.VISIBLE
            btnReload.visibility = View.GONE
            intent.getStringExtra(NAME)?.let { it1 -> mPresenter.loadRepository(it1) }
        }
    }

    override fun showReloadButton() {
        pbDetails.visibility = View.GONE
        btnReload.visibility = View.VISIBLE
    }

}