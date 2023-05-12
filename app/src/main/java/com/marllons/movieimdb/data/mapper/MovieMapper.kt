package com.marllons.movieimdb.data.mapper

import com.marllons.movieimdb.data.model.response.ImdbMovieResponse
import com.marllons.movieimdb.data.model.response.MovieResponse
import com.marllons.movieimdb.domain.entity.ImdbMovie
import com.marllons.movieimdb.domain.entity.Movie
import com.marllons.movieimdb.presenter.entity.UiImdbMovie
import com.marllons.movieimdb.presenter.entity.UiMovie

fun MovieResponse.toEntityDomain() = Movie(
    title = this.title,
    year = this.year,
    imdbid = this.imdbid,
    type = this.type,
    poster = this.poster,
)

fun Movie.toEntityPresenter() = UiMovie(
    title = this.title,
    year = this.year,
    imdbid = this.imdbid,
    type = this.type,
    poster = this.poster,
)

fun ImdbMovie.toEntityPresenter() = UiImdbMovie(
    title = this.title,
    year = this.year,
    released = this.released,
    runtime = this.runtime,
    genre = this.genre,
    director = this.director,
    writer = this.writer,
    actors = this.actors,
    plot = this.plot,
    language = this.language,
    country = this.country,
    awards = this.awards,
    poster = this.poster,
    imdbId = this.imdbId,
)

fun ImdbMovieResponse.toEntityDomain() = ImdbMovie(
    title = this.title,
    year = this.year,
    released = this.released,
    runtime = this.runtime,
    genre = this.genre,
    director = this.director,
    writer = this.writer,
    actors = this.actors,
    plot = this.plot,
    language = this.language,
    country = this.country,
    awards = this.awards,
    poster = this.poster,
    imdbId = this.imdbId,
)