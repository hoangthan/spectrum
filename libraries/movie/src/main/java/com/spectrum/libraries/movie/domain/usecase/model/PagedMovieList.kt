package com.spectrum.libraries.movie.domain.usecase.model

data class PagedMovieList(
    val page: Int,
    val totalPages: Int,
    val movies: List<Movie>
)
