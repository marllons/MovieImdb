package com.marllons.movieimdb.data.repository

import androidx.paging.PagingData
import com.marllons.movieimdb.domain.entity.ImdbMovie
import com.marllons.movieimdb.domain.entity.Movie
import com.marllons.mshttp.result.Result
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMoviesListByTitle(title: String): Flow<PagingData<Movie>>
    suspend fun getMovieByImdb(imdb: String): Result<ImdbMovie>
}