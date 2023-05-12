package com.marllons.movieimdb.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marllons.movieimdb.data.mapper.toEntityDomain
import com.marllons.movieimdb.data.service.MovieService
import com.marllons.movieimdb.domain.entity.Movie
import com.marllons.mshttp.result.RetrofitResult
import java.io.IOException
import retrofit2.HttpException

class MoviePagingSource(
    private val service: MovieService,
    private val result: RetrofitResult,
    private val title: String
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {
            val positionPage = params.key ?: 1
            val res = result.getResult {
                service.getMoviesByTitle(title, positionPage)
            }
            val totalResults = res.getOrNull()?.totalResults?.toInt() ?: 0
            val data = res.getOrNull()?.list?.map {
                it.toEntityDomain()
            }

            return LoadResult.Page(
                data = data ?: listOf(),
                prevKey = null,
                nextKey = if (positionPage * TOTAL_ELEMENTS_PER_PAGE >= totalResults) null else positionPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        const val TOTAL_ELEMENTS_PER_PAGE = 10
    }
}
