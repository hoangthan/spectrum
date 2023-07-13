package com.spectrum.libraries.movie.domain.usecase

import com.spectrum.libraries.movie.domain.model.Movie
import com.spectrum.libraries.movie.domain.repository.GenresRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieAttachInfoUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {

    suspend fun attachGenresToMovie(movie: Movie): Movie {
        val genres = movie.genresIds.mapNotNull { genresRepository.getGenresById(it) }
        return movie.copy(genres = genres)
    }
}
