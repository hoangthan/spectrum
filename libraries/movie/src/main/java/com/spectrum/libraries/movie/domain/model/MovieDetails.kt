package com.spectrum.libraries.movie.domain.model


data class MovieDetails(
    val id: Long,
    val adult: Boolean?,
    val backdropPath: String?,
    val budget: Int?,
    val genres: List<Genres>,
    val homepage: String?,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val revenue: Long?,
    val runtime: Int?,
    val spokenLanguages: List<String>,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?,
)
