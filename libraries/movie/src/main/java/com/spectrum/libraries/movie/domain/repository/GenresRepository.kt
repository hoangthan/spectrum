package com.spectrum.libraries.movie.domain.repository

import com.spectrum.libraries.movie.domain.model.Genres

interface GenresRepository {
    suspend fun getAllGenres(): List<Genres>

    suspend fun getGenresById(id: Int): Genres?
}
