package com.marllons.movieimdb.data.db

import androidx.room.*
import com.marllons.movieimdb.Constants.FAVORITES_TABLE_NAME
import com.marllons.movieimdb.data.db.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM $FAVORITES_TABLE_NAME")
    suspend fun loadFavorites(): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favoriteEntity: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)
}