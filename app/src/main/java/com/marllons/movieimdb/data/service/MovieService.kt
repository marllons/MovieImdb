package com.marllons.movieimdb.data.service

import com.marllons.movieimdb.data.model.response.ImdbMovieResponse
import com.marllons.movieimdb.data.model.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/")
    suspend fun getMoviesByTitle(
        @Query("s") title: String,
        @Query("page") page: Int?,
    ): Response<MoviesResponse>

    @GET("/")
    suspend fun getMovieByImdb(
        @Query("i") title: String,
        @Query("plot") plot: String?,
    ): Response<ImdbMovieResponse>
}