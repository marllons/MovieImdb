package com.marllons.movieimdb.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    @SerializedName("Title")
    val title: String?,
    @SerializedName("Year")
    val year: String?,
    @SerializedName("imdbID")
    val imdbid: String?,
    @SerializedName("Type")
    val type: String?,
    @SerializedName("Poster")
    val poster: String?,
) : Parcelable


