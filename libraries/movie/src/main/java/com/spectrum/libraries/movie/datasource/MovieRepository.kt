package com.spectrum.libraries.movie.datasource

import com.spectrum.libraries.core.usecase.UseCaseResult
import com.spectrum.libraries.movie.domain.usecase.model.MovieSource
import com.spectrum.libraries.movie.domain.usecase.model.PagedMovieList

interface MovieRepository {
    suspend fun getLiveMovies(type: MovieSource, page: Int): UseCaseResult<PagedMovieList>

    suspend fun searchMovie(name: String, page: Int): UseCaseResult<PagedMovieList>
}
