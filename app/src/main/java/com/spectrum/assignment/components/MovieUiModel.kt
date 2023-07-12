package com.spectrum.assignment.components

import com.spectrum.libraries.movie.domain.usecase.model.Movie

data class MovieUiModel(
    val id: Int,
    val adult: Boolean?,
    val backdropPath: String?,
    val genres: List<GenresUiModel>,
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

fun Movie.toUiModel(): MovieUiModel? {
    id ?: return null //Do not allow if the unique field is null

    return MovieUiModel(
        id = id!!,
        adult = adult,
        backdropPath = backdropPath,
        genres = listOf(),
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}