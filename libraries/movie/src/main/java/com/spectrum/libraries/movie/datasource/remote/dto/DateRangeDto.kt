package com.spectrum.libraries.movie.datasource.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class DateRangeDto(
    @Json(name = "maximum")
    val maximum: String? = null,

    @Json(name = "minimum")
    val minimum: String? = null
)
