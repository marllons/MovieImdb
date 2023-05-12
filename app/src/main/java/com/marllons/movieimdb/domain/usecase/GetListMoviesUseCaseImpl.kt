package com.marllons.movieimdb.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.marllons.movieimdb.data.mapper.toEntityPresenter
import com.marllons.movieimdb.data.repository.MovieRepository
import com.marllons.movieimdb.domain.entity.Movie
import com.marllons.movieimdb.presenter.entity.UiMovie
import com.marllons.mshttp.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetListMoviesUseCaseImpl(
    private val repository: MovieRepository
): GetListMoviesUseCase {
    override suspend fun invoke(title: String): Flow<PagingData<UiMovie>> {
        return repository.getMoviesListByTitle(title).map { listUiMovie ->
            listUiMovie.map {
                it.toEntityPresenter()
            }
        }
    }
}