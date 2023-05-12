package com.marllons.movieimdb.domain.usecase

import com.marllons.movieimdb.data.mapper.toEntityPresenter
import com.marllons.movieimdb.data.repository.MovieRepository
import com.marllons.movieimdb.presenter.entity.UiImdbMovie
import com.marllons.mshttp.result.Result

class GetFilmByImdbUseCaseImpl(
    private val repository: MovieRepository
): GetFilmByImdbUseCase {

    override suspend fun invoke(imdbid: String): Result<UiImdbMovie> {
        return repository.getMovieByImdb(imdbid).map {
            it.toEntityPresenter()
        }
    }
}