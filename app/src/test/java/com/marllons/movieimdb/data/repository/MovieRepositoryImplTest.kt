package com.marllons.movieimdb.data.repository

import com.marllons.movieimdb.data.mapper.toEntityDomain
import com.marllons.movieimdb.data.model.response.ImdbMovieResponse
import com.marllons.movieimdb.data.service.MovieService
import com.marllons.mshttp.result.GENERIC_ERROR
import com.marllons.mshttp.result.RetrofitResultImpl
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response
import kotlin.random.Random

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MovieRepositoryImplTest {
    private val retrofitResult = RetrofitResultImpl(mockk(relaxed = true))
    private val service: MovieService = mockk()
    private val subject = MovieRepositoryImpl(retrofitResult, service)

    @After
    fun resetMocks() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN a call on getUser THEN call a service`() = runTest {
        coEvery { service.getMovieByImdb("test", "full") } returns Response.error(400, mockk(relaxed = true))
        subject.getMovieByImdb("test")
        coVerify(exactly = 1) {
            service.getMovieByImdb("test", "full")
        }
    }

    @Test
    fun `GIVEN a call on getUser WHEN has an error on service THEN return error`() = runTest {
        coEvery { service.getMovieByImdb(any(), any()) } returns Response.error(400, mockk(relaxed = true))
        val result = subject.getMovieByImdb("test")
        assertEquals(GENERIC_ERROR, result.errorOrNull())
    }


    @Test
    fun `GIVEN a call on getUser WHEN has a success on service THEN return success mapped`() = runTest {
        val responseMock = mockk<ImdbMovieResponse>(relaxed = true) {
            every { this@mockk.imdbId } returns Random.nextInt().toString()
        }
        val expected = responseMock.toEntityDomain()
        coEvery { service.getMovieByImdb("test", "full") } returns Response.success(responseMock)
        val result = subject.getMovieByImdb("test")
        assertEquals(expected, result.getOrNull())
        coVerify(exactly = 1) {
            service.getMovieByImdb("test", "full")
        }
    }
}