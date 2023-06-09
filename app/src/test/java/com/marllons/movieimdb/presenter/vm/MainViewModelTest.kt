package com.marllons.movieimdb.presenter.vm

import androidx.paging.PagingData
import androidx.paging.map
import com.marllons.movieimdb.domain.usecase.GetListMoviesUseCase
import com.marllons.movieimdb.presenter.entity.UiMovie
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class MainViewModelTest {
    private val useCase = mockk<GetListMoviesUseCase>()
    private lateinit var viewModel: MainViewModel
    private val pagingDataMovies = PagingData.from(
        listOf(UiMovie(title = "spiderman", year = "2023", imdbid = "7442hsda", type = "movie", poster = null))
    )

    @Before
    fun setUp() {
        viewModel = MainViewModel(useCase)
    }

    @Test
    fun `WHEN call a function getListUiMovies THEN te paging data SHOULD validate with the corrects movies`() =
        runTest {
            coEvery {
                useCase.invoke("spiderman")
            } returns  flowOf(pagingDataMovies)

            viewModel.getListUiMovies("spiderman")

            viewModel.listUiMovies.value?.first()?.map { expectedMovie ->
                pagingDataMovies.map {
                    Assert.assertEquals(expectedMovie, it)
                }
            }

        }

}