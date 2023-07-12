package com.spectrum.libraries.movie.datasource.remote.dto

import com.spectrum.libraries.movie.domain.model.Genres
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreDto(
    @Json(name = "id")
    val id: Long? = null,

    @Json(name = "name")
    val name: String? = null
)

fun GenreDto.toDomain(): Genres? {
    if (id == null || name == null) return null
    return Genres(id = id, name = name)
}
