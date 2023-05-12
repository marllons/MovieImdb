package com.marllons.movieimdb.presenter.ui.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class MovieLoaderAdapter : LoadStateAdapter<MovieLoaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): MovieLoaderViewHolder {
        return MovieLoaderViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: MovieLoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


}