package com.marllons.movieimdb.presenter.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiMovie(
    val title: String?,
    val year: String?,
    val imdbid: String?,
    val type: String?,
    val poster: String?,
): Parcelable
