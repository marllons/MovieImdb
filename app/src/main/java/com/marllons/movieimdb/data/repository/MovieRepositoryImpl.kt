package com.marllons.movieimdb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.marllons.movieimdb.data.mapper.toEntityDomain
import com.marllons.movieimdb.data.paging.MoviePagingSource
import com.marllons.movieimdb.data.service.MovieService
import com.marllons.movieimdb.domain.entity.ImdbMovie
import com.marllons.movieimdb.domain.entity.Movie
import com.marllons.mshttp.result.Result
import com.marllons.mshttp.result.RetrofitResult
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val result: RetrofitResult,
    private val service: MovieService
) : MovieRepository {

    companion object {
        const val PAGE_SIZE = 10
        const val PLOT = "full"
    }

    override suspend fun getMoviesListByTitle(title: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE
            ),
            pagingSourceFactory = { MoviePagingSource(service, result, title) }
        ).flow
    }

    override suspend fun getMovieByImdb(imdb: String): Result<ImdbMovie> {
        return result.getResult {
            service.getMovieByImdb(imdb, PLOT)
        }.map {
            it.toEntityDomain()
        }
    }
}