package com.marllons.movieimdb.presenter.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.marllons.movieimdb.domain.usecase.GetListMoviesUseCase
import com.marllons.movieimdb.presenter.entity.UiMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getListMoviesUseCase: GetListMoviesUseCase
) : ViewModel() {

    private val _listUiMovies = MutableLiveData<Flow<PagingData<UiMovie>>>()
    val listUiMovies: LiveData<Flow<PagingData<UiMovie>>> = _listUiMovies

    fun getListUiMovies(title: String) {
        viewModelScope.launch {
            _listUiMovies.postValue(getListMoviesUseCase(title).cachedIn(viewModelScope))
        }
    }
}