package com.marllons.movieimdb.presenter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.marllons.movieimdb.R
import com.marllons.movieimdb.databinding.ItemMovieBinding
import com.marllons.movieimdb.databinding.ItemMovieLoadingBinding

class MovieLoaderViewHolder(
    private val binding: ItemMovieLoadingBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        binding.progressBar.isVisible = loadState is LoadState.Loading
//        when(loadState) {
//            is LoadState.Loading -> {
//                binding.progressBar.isVisible = true
//            }
//            else -> {
//                binding.progressBar.isVisible = false
//            }
//        }
    }

    companion object {
        fun inflate(parent: ViewGroup) : MovieLoaderViewHolder {
            return MovieLoaderViewHolder(
                ItemMovieLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }
}