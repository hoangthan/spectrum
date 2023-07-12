package com.spectrum.features.movie.ui.components

import com.spectrum.libraries.movie.domain.model.Genres

data class GenresUiModel(
    val id: Int,
    val name: String,
)

fun Genres.toUiModel(): GenresUiModel {
    return GenresUiModel(id = id, name = name)
}
