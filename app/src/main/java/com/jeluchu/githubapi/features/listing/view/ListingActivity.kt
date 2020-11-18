package com.jeluchu.githubapi.features.listing.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import com.jeluchu.githubapi.features.details.view.DetailActivity
import com.jeluchu.githubapi.features.listing.models.Repository
import com.jeluchu.githubapi.core.platform.BaseActivity
import com.jeluchu.githubapi.features.listing.logic.ListPresenter
import com.jeluchu.githubapi.R
import kotlinx.android.synthetic.main.activity_organization.*
import java.util.*


class ListingActivity : BaseActivity<ListViewController.View,
        ListPresenter>(),
        ListViewController.View {

    private var mAdapter: ListAdapter? = null
    override var mPresenter: ListPresenter = ListPresenter()

    override fun showOrganizations(repositories: MutableList<Repository>) {
        mAdapter?.addRepositories(repositories)
        mAdapter?.notifyDataSetChanged()
        hideProgress()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organization)
        setUpRecyclerView()
        mPresenter.loadRepositories()

        toolbar.title = getString(R.string.title_activity_repositories)
        showProgress()
        fab.setOnClickListener {
            showProgress()
            mPresenter.loadRepositories()
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = ListAdapter(ArrayList<Repository>()) {
            startActivity(DetailActivity.newIntent(this, it.name))
        }
        rvListing.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvListing.adapter = mAdapter
    }

    private fun showProgress() {
        rvListing.visibility = View.GONE
        pbList.visibility = View.VISIBLE
        fab.isEnabled = false
    }

    private fun hideProgress() {
        rvListing.visibility = View.VISIBLE
        pbList.visibility = View.GONE
        fab.isEnabled = true
    }

    override fun showError(error: String?) {
        super.showError(error)
        pbList.visibility = View.GONE
        fab.isEnabled = true
    }
}

