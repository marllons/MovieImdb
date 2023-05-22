package com.marllons.movieimdb.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marllons.movieimdb.Constants.FAVORITES_COLUMN_INFO_ID
import com.marllons.movieimdb.Constants.FAVORITES_COLUMN_INFO_IMAGE_URL
import com.marllons.movieimdb.Constants.FAVORITES_COLUMN_INFO_IMDBID
import com.marllons.movieimdb.Constants.FAVORITES_COLUMN_INFO_TITLE
import com.marllons.movieimdb.Constants.FAVORITES_COLUMN_INFO_TYPE
import com.marllons.movieimdb.Constants.FAVORITES_COLUMN_INFO_YEAR
import com.marllons.movieimdb.Constants.FAVORITES_TABLE_NAME
import com.marllons.movieimdb.domain.entity.Movie

@Entity(tableName = FAVORITES_TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = FAVORITES_COLUMN_INFO_ID)
    val id: Int = 0,
    @ColumnInfo(name = FAVORITES_COLUMN_INFO_IMDBID)
    val idImdb: String,
    @ColumnInfo(name = FAVORITES_COLUMN_INFO_TITLE)
    val title: String,
    @ColumnInfo(name = FAVORITES_COLUMN_INFO_YEAR)
    val year: String,
    @ColumnInfo(name = FAVORITES_COLUMN_INFO_IMAGE_URL)
    val imageUrl: String,
    @ColumnInfo(name = FAVORITES_COLUMN_INFO_TYPE)
    val type: String,
)

fun FavoriteEntity.toMovieModel() = Movie(
    imdbid = idImdb,
    poster = imageUrl,
    title = title,
    year = year,
    type = type
)