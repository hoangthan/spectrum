package com.spectrum.libraries.movie.domain.repository

import com.spectrum.libraries.core.usecase.UseCaseResult
import com.spectrum.libraries.movie.domain.model.MovieDetails
import com.spectrum.libraries.movie.domain.model.MovieSource
import com.spectrum.libraries.movie.domain.model.PagedMovieList

interface MovieRepository {
    suspend fun getLiveMovies(type: MovieSource, page: Int): UseCaseResult<PagedMovieList>

    suspend fun searchMovie(name: String, page: Int): UseCaseResult<PagedMovieList>

    suspend fun getMovieDetails(id: Long): UseCaseResult<MovieDetails>
}
