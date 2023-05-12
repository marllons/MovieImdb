package com.marllons.movieimdb.presenter.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marllons.movieimdb.domain.usecase.GetFilmByImdbUseCase
import com.marllons.movieimdb.presenter.entity.UiImdbMovie
import com.marllons.mshttp.utils.singleEvent.SingleLiveDataEvent
import com.marllons.mshttp.utils.viewState.ViewState
import com.marllons.mshttp.utils.viewState.postFailure
import com.marllons.mshttp.utils.viewState.postLoading
import com.marllons.mshttp.utils.viewState.postSuccess
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getFilmByImdbUseCase: GetFilmByImdbUseCase,
) : ViewModel() {
    private val _getMovieByImdb = SingleLiveDataEvent<ViewState<UiImdbMovie>>()
    val getMovieByImdb: LiveData<ViewState<UiImdbMovie>> = _getMovieByImdb

    fun getMovieByImdbId(imdbId: String) {
        viewModelScope.launch {
            _getMovieByImdb.postLoading()
            getFilmByImdbUseCase(imdbId)
                .onSuccess { _getMovieByImdb.postSuccess(it) }
                .onFailure { _getMovieByImdb.postFailure(it) }
        }
    }
}