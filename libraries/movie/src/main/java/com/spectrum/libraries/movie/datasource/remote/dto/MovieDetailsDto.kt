package com.spectrum.libraries.movie.datasource.remote.dto

import com.spectrum.libraries.movie.domain.model.MovieDetails
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MovieDetailsDto(
    @Json(name = "adult")
    val adult: Boolean? = null,

    @Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @Json(name = "budget")
    val budget: Int? = null,

    @Json(name = "genres")
    val genres: List<GenreDto?>? = null,

    @Json(name = "homepage")
    val homepage: String? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "imdb_id")
    val imdbId: String? = null,

    @Json(name = "original_language")
    val originalLanguage: String? = null,

    @Json(name = "original_title")
    val originalTitle: String? = null,

    @Json(name = "overview")
    val overview: String? = null,

    @Json(name = "popularity")
    val popularity: Double? = null,

    @Json(name = "poster_path")
    val posterPath: String? = null,

    @Json(name = "release_date")
    val releaseDate: String? = null,

    @Json(name = "revenue")
    val revenue: Int? = null,

    @Json(name = "runtime")
    val runtime: Int? = null,

    @Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguageDto?>? = null,

    @Json(name = "status")
    val status: String? = null,

    @Json(name = "tagline")
    val tagline: String? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "video")
    val video: Boolean? = null,

    @Json(name = "vote_average")
    val voteAverage: Double? = null,

    @Json(name = "vote_count")
    val voteCount: Int? = null
)

@JsonClass(generateAdapter = true)
data class SpokenLanguageDto(
    @Json(name = "english_name")
    val englishName: String? = null,
)

fun MovieDetailsDto.toDomain(): MovieDetails {
    return MovieDetails(
        adult = adult,
        backdropPath = backdropPath,
        budget = budget,
        genres = genres?.filterNotNull()?.mapNotNull { it.toDomain() } ?: emptyList(),
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
        spokenLanguages = spokenLanguages?.mapNotNull { it?.englishName } ?: emptyList(),
    )
}
