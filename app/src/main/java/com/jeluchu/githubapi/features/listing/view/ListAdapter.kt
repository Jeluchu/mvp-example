package com.jeluchu.githubapi.features.listing.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jeluchu.githubapi.features.listing.models.Repository
import com.jeluchu.githubapi.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repository.*

class ListAdapter(private val repositories: MutableList<Repository>,
                  private val onClick: (Repository) -> Unit)
    : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(repositories[position])
    }

    override fun getItemCount(): Int = repositories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repository, parent, false)
                    .let { ViewHolder(it, onClick) }

    class ViewHolder(override val containerView: View, private val onClick: (Repository) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(repository: Repository) {
            with(repository) {
                tvTitle.text = name
                tvDescription.text = description
                containerView.setOnClickListener { onClick(this) }
            }
        }
    }

    fun addRepositories(newRepositories: List<Repository>) = repositories.addAll(newRepositories)

}