package com.spectrum.libraries.movie.domain.model

data class Movie(
    val id: Long?,
    val adult: Boolean?,
    val backdropPath: String?,
    val genres: List<Genres>,
    val genresIds: List<Int>,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?,
)
