package com.marllons.movieimdb.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImdbMovieResponse(
    @SerializedName("Title")
    val title: String?,
    @SerializedName("Year")
    val year: String?,
    @SerializedName("Released")
    val released: String?,
    @SerializedName("Runtime")
    val runtime: String?,
    @SerializedName("Genre")
    val genre: String?,
    @SerializedName("Director")
    val director: String?,
    @SerializedName("Writer")
    val writer: String?,
    @SerializedName("Actors")
    val actors: String?,
    @SerializedName("Plot")
    val plot: String?,
    @SerializedName("Language")
    val language: String?,
    @SerializedName("Country")
    val country: String?,
    @SerializedName("Awards")
    val awards: String?,
    @SerializedName("Poster")
    val poster: String?,
    @SerializedName("imdbID")
    val imdbId: String?,
): Parcelable
