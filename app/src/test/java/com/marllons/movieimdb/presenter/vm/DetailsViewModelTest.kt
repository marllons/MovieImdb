package com.marllons.movieimdb.presenter.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marllons.movieimdb.domain.usecase.GetFilmByImdbUseCase
import com.marllons.movieimdb.presenter.entity.UiImdbMovie
import com.marllons.mshttp.domain.model.MSHttpError
import com.marllons.mshttp.result.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class DetailsViewModelTest {
    private val useCase = mockk<GetFilmByImdbUseCase>()
    private lateinit var viewModel: DetailsViewModel
    private val movie = UiImdbMovie(title = "test")
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = DetailsViewModel(useCase)
    }

    @Test
    fun `WHEN call a function getFilmByImdb THEN the data SHOULD validate with the correct movie`() =
        runTest {
            coEvery {
                useCase.invoke("999")
            } returns  Result.success(movie)

            viewModel.getMovieByImdbId("999")

            assertEquals(movie, viewModel.getMovieByImdb.value?.data)
        }


    @Test
    fun `WHEN call a function getFilmByImdb THEN the data SHOULD validate with the failure`() =
        runTest {
            val error = MSHttpError()
            coEvery {
                useCase.invoke("999")
            } returns  Result.failure(error)

            viewModel.getMovieByImdbId("999")

            assertEquals(error, viewModel.getMovieByImdb.value?.failure)
        }

}