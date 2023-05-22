package com.marllons.movieimdb.data.repository

import com.marllons.movieimdb.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    suspend fun getFavorites(): Flow<List<Movie>>
    suspend fun saveFavorite(movie: Movie)
    suspend fun deleteFavorite(movie: Movie)
}