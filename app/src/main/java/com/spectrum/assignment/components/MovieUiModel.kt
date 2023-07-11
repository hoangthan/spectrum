package com.spectrum.assignment.components

data class MovieUiModel(
    val adult: Boolean,
    val backdropPath: String,
    val genres: List<GenresUiModel>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
)
