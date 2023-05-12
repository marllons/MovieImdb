package com.marllons.movieimdb.presenter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.marllons.movieimdb.R
import com.marllons.movieimdb.databinding.FragmentDetailsBinding
import com.marllons.movieimdb.presenter.entity.UiImdbMovie
import com.marllons.movieimdb.presenter.vm.DetailsViewModel
import com.marllons.movieimdb.utils.viewBinding
import com.marllons.mshttp.domain.model.MSHttpError
import org.koin.android.ext.android.inject

class DetailsFragment: Fragment() {
    private var binding: FragmentDetailsBinding by viewBinding()
    private val viewModel: DetailsViewModel by inject()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservables()
        args.subject?.let {  viewModel.getMovieByImdbId(it) }
    }

    private fun setupObservables() {
        viewModel.getMovieByImdb.observe(viewLifecycleOwner) {
            it.handleIt(
                onSuccess = ::handleSuccess,
                onFailure = ::handleFailure,
                isLoading = ::handleLoading
            )
        }
    }

    private fun handleLoading(loading: Boolean) {
        binding.run {
            layoutProgress.root.isVisible = loading
        }
    }

    private fun handleFailure(msHttpError: MSHttpError) {
        binding.run {
            layoutError.root.isVisible = true
            layoutError.textViewError.text = msHttpError.title
            layoutError.tryAgainButton.setOnClickListener {
                args.subject?.let { viewModel.getMovieByImdbId(it) }
            }
        }

    }

    private fun handleSuccess(uiImdbMovie: UiImdbMovie) {
        binding.run {
            layoutError.root.isVisible = false
            textViewTitle.text = uiImdbMovie.title
            textViewActors.text = uiImdbMovie.actors
            textViewCountry.text = uiImdbMovie.country
            textViewDirector.text = uiImdbMovie.director
            textViewGenre.text = uiImdbMovie.genre
            textViewImdb.text = uiImdbMovie.imdbId
            textViewLanguage.text = uiImdbMovie.language
            textViewPlot.text = uiImdbMovie.plot
            textViewReleased.text = uiImdbMovie.released
            textViewRuntime.text = uiImdbMovie.runtime
            textViewWriter.text = uiImdbMovie.writer
            textViewYear.text = uiImdbMovie.year

            Glide.with(root.context)
                .load(uiImdbMovie.poster.orEmpty())
                .error(R.drawable.baseline_hide_image_96)
                .placeholder(R.drawable.baseline_image_24)
                .into(imageViewPoster)
        }
    }
}
