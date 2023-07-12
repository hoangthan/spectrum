package com.spectrum.libraries.movie.datasource.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GetGenresResponse(
    @Json(name = "genres")
    val genres: List<GenreDto>? = null
)
