package com.marllons.movieimdb.domain.usecase

import com.marllons.movieimdb.data.repository.MovieRepository
import com.marllons.movieimdb.domain.entity.ImdbMovie
import com.marllons.movieimdb.presenter.entity.UiImdbMovie
import com.marllons.mshttp.result.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class GetFilmByImdbUseCaseImplTest {
    private val repository = mockk<MovieRepository>()
    private lateinit var useCase: GetFilmByImdbUseCaseImpl
    private val movie = ImdbMovie(title = "test")
    private val movieExpected = UiImdbMovie(title = "test")

    @Before
    fun setUp() {
        useCase = GetFilmByImdbUseCaseImpl(repository)
    }

    @Test
    fun `WHEN call invoke function THEN the result SHOULD be correct`() = runTest {
        coEvery { repository.getMovieByImdb("999") } returns Result.success(movie)
        val result = useCase.invoke("999")
        assertEquals(Result.success(movieExpected), result)
    }

}