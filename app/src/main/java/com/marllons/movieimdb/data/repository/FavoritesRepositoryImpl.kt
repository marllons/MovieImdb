package com.marllons.movieimdb.data.repository

import com.marllons.movieimdb.data.db.FavoriteDao
import com.marllons.movieimdb.data.db.entity.FavoriteEntity
import com.marllons.movieimdb.data.db.entity.toMovieModel
import com.marllons.movieimdb.domain.entity.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl(
    private val favoriteDao: FavoriteDao
): FavoritesRepository {

    override suspend fun getFavorites(): Flow<List<Movie>> {
        return favoriteDao.loadFavorites().map { list ->
            list.map { it.toMovieModel() }
        }
    }

    override suspend fun saveFavorite(movie: Movie) {
        return favoriteDao.addFavorite(FavoriteEntity(idImdb = movie.imdbid.orEmpty(), title = movie.title.orEmpty(), year = movie.year.orEmpty(), imageUrl = movie.poster.orEmpty(), type = movie.type.orEmpty()))
    }

    override suspend fun deleteFavorite(movie: Movie) {
        return favoriteDao.deleteFavorite(FavoriteEntity(idImdb = movie.imdbid.orEmpty(), title = movie.title.orEmpty(), year = movie.year.orEmpty(), imageUrl = movie.poster.orEmpty(), type = movie.type.orEmpty()))
    }
}