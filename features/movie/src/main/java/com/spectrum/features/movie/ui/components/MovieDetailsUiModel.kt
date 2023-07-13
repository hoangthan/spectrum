package com.spectrum.features.movie.ui.components

import com.spectrum.libraries.movie.domain.model.Genres
import com.spectrum.libraries.movie.domain.model.MovieDetails

data class MovieDetailsUiModel(
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
    val revenue: Int?,
    val runtime: Int?,
    val spokenLanguages: List<String>,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?,
)

fun MovieDetails.toUi(): MovieDetailsUiModel {
    return MovieDetailsUiModel(
        adult = adult,
        backdropPath = backdropPath,
        budget = budget,
        genres = genres,
        homepage = homepage,
        id = id,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        spokenLanguages = spokenLanguages
    )
}
