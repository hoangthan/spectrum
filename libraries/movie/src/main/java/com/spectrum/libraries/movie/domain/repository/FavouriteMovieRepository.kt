package com.spectrum.libraries.movie.domain.repository

import com.spectrum.libraries.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavouriteMovieRepository {

    suspend fun save(movie: Movie)

    suspend fun delete(movie: Movie)

    fun getAll(): Flow<List<Movie>>

    fun getByIds(vararg ids: Long): Flow<List<Movie>>
}
