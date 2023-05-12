package com.marllons.movieimdb.domain.usecase

import androidx.paging.PagingData
import com.marllons.movieimdb.data.repository.MovieRepository
import com.marllons.movieimdb.domain.entity.Movie
import com.marllons.movieimdb.presenter.entity.UiMovie
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class GetListMoviesUseCaseImplTest {
    private val repository = mockk<MovieRepository>()
    private lateinit var useCase: GetListMoviesUseCaseImpl
    private val pagingDataMovies = PagingData.from(
        listOf(Movie(title = "spiderman", year = "2023", imdbid = "7442hsda", type = "movie", poster = null))
    )

    private val pagingDataUiMovies = PagingData.from(
        listOf(UiMovie(title = "spiderman", year = "2023", imdbid = "7442hsda", type = "movie", poster = null))
    )

    @Before
    fun setUp() {
        useCase = GetListMoviesUseCaseImpl(repository)
    }

    @Test
    fun `WHEN call invoke function THEN the result SHOULD be correct`() = runTest {
        coEvery { repository.getMoviesListByTitle("999") } returns flowOf(pagingDataMovies)
        val result = useCase.invoke("999")
        assertEquals(flowOf(pagingDataUiMovies), result)
    }

}