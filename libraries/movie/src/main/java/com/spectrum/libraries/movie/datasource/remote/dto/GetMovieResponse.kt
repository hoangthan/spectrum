package com.spectrum.libraries.movie.datasource.remote.dto

import com.spectrum.libraries.movie.domain.model.PagedMovieList
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GetMovieResponseDto(
    @Json(name = "dates")
    val dates: DateRangeDto? = null,

    @Json(name = "page")
    val page: Int? = null,

    @Json(name = "results")
    val results: List<MovieDto?>? = null,

    @Json(name = "total_pages")
    val totalPages: Int? = null,

    @Json(name = "total_results")
    val totalResults: Int? = null
)

fun GetMovieResponseDto.toPagedMoveList(): PagedMovieList {
    return PagedMovieList(
        page = page ?: 0,
        totalPages = totalPages ?: 0,
        movies = results?.mapNotNull { it?.toDomain() } ?: listOf()
    )
}
