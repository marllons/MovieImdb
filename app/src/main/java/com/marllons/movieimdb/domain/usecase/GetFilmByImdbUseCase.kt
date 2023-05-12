package com.marllons.movieimdb.domain.usecase

import com.marllons.movieimdb.presenter.entity.UiImdbMovie
import com.marllons.mshttp.result.Result

interface GetFilmByImdbUseCase {
    suspend operator fun invoke(imdbid: String) : Result<UiImdbMovie>
}