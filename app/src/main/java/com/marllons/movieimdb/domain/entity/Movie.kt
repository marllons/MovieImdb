package com.marllons.movieimdb.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String?,
    val year: String?,
    val imdbid: String?,
    val type: String?,
    val poster: String?,
) : Parcelable
