package com.marllons.movieimdb.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marllons.movieimdb.data.db.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}