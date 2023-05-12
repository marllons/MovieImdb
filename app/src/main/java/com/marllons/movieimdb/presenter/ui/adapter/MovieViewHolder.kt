package com.marllons.movieimdb.presenter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marllons.movieimdb.R
import com.marllons.movieimdb.databinding.ItemMovieBinding
import com.marllons.movieimdb.presenter.entity.UiMovie

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val callback: (String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(uiMovie: UiMovie?) {
        binding.run {
            textViewTitle.text = uiMovie?.title ?: "titulo"
            year.text = uiMovie?.year ?: "ano"
            imdbid.text = uiMovie?.imdbid ?: "imdbid"

            Glide.with(root.context)
                .load(uiMovie?.poster.orEmpty())
                .error(R.drawable.baseline_hide_image_96)
                .placeholder(R.drawable.baseline_image_24)
                .into(imageView)

            card.setOnClickListener {
                callback.invoke(uiMovie?.imdbid ?: "imdbid")
            }
        }

    }

    companion object {
        fun inflate(parent: ViewGroup, callback: (String) -> Unit): MovieViewHolder {
            return MovieViewHolder(
                ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                callback
            )
        }
    }
}