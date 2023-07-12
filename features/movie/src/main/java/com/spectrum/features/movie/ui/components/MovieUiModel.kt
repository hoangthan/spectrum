package com.spectrum.features.movie.ui.components

import com.spectrum.libraries.movie.domain.model.Movie


data class MovieUiModel(
    val id: Long,
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
        genres = genres.map { it.toUiModel() },
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
