package com.spectrum.libraries.movie.datasource.repositoryImpl

import com.skydoves.sandwich.mapSuccess
import com.spectrum.libraries.core.usecase.UseCaseResult
import com.spectrum.libraries.movie.datasource.remote.apiService.GenresApiService
import com.spectrum.libraries.movie.datasource.remote.dto.toDomain
import com.spectrum.libraries.movie.domain.model.Genres
import com.spectrum.libraries.movie.domain.repository.GenresRepository
import com.spectrum.libraries.network.utils.toUseCaseResult
import javax.inject.Inject

class GenresRepositoryImpl @Inject constructor(
    private val genresApiService: GenresApiService
) : GenresRepository {

    override suspend fun getAllGenres(): List<Genres> {
        val result = genresApiService.getAll()
            .mapSuccess { genres ?: listOf() }
            .mapSuccess { mapNotNull { it.toDomain() } }
            .toUseCaseResult()

        return when (result) {
            is UseCaseResult.Failure -> listOf()
            is UseCaseResult.Success -> result.data
        }
    }

    override suspend fun getGenresById(id: Long): Genres? {
        return getAllGenres().firstOrNull { it.id == id }
    }
}
