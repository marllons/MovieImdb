package com.marllons.movieimdb.presenter.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.marllons.movieimdb.presenter.entity.UiMovie

class MovieAdapter(
    private val callback: (String) -> Unit
): PagingDataAdapter<UiMovie, MovieViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.inflate(parent, callback)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UiMovie>() {
            override fun areItemsTheSame(oldItem: UiMovie, newItem: UiMovie) = oldItem.hashCode() == newItem.hashCode()
            override fun areContentsTheSame(oldItem: UiMovie, newItem: UiMovie) = oldItem == newItem
        }
    }
}