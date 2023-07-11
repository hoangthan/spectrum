package com.spectrum.libraries.movie.datasource.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GetMovieResponseDto(
    @Json(name = "dates")
    val dates: DateRangeDto? = null,

    @Json(name = "page")
    val page: Int? = null,

    @Json(name = "results")
    val results: List<Result?>? = null,

    @Json(name = "total_pages")
    val totalPages: Int? = null,

    @Json(name = "total_results")
    val totalResults: Int? = null
)

@JsonClass(generateAdapter = true)
data class DateRangeDto(
    @Json(name = "maximum")
    val maximum: String? = null,

    @Json(name = "minimum")
    val minimum: String? = null
)

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "adult")
    val adult: Boolean? = null,

    @Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @Json(name = "genre_ids")
    val genreIds: List<Int?>? = null,

    @Json(name = "id")
    val id: Int? = null,

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

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "video")
    val video: Boolean? = null,

    @Json(name = "vote_average")
    val voteAverage: Double? = null,

    @Json(name = "vote_count")
    val voteCount: Int? = null
)
