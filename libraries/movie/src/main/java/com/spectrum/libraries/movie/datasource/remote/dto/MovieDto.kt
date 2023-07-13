package com.spectrum.libraries.movie.datasource.remote.dto

import com.spectrum.libraries.movie.domain.model.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDto(
    @Json(name = "adult")
    val adult: Boolean? = null,

    @Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @Json(name = "genre_ids")
    val genreIds: List<Int?>? = null,

    @Json(name = "id")
    val id: Long? = null,

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

fun MovieDto.toDomain(): Movie {
    return Movie(
        adult = adult,
        backdropPath = backdropPath,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        genresIds = genreIds?.filterNotNull() ?: emptyList(),
        genres = emptyList(),
    )
}
