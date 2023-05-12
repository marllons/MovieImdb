package com.marllons.movieimdb.domain.usecase

import androidx.paging.PagingData
import com.marllons.movieimdb.presenter.entity.UiMovie
import com.marllons.mshttp.result.Result
import kotlinx.coroutines.flow.Flow

interface GetListMoviesUseCase {
    suspend operator fun invoke(title: String): Flow<PagingData<UiMovie>>
}