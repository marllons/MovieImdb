package com.marllons.movieimdb.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesResponse(
    @SerializedName("Search")
    val list: List<MovieResponse>,
    @SerializedName("totalResults")
    val totalResults: String?
) : Parcelable