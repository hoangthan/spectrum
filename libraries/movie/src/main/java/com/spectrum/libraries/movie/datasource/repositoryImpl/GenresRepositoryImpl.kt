package com.spectrum.libraries.movie.datasource.repositoryImpl

import com.skydoves.sandwich.mapSuccess
import com.skydoves.sandwich.suspendOnSuccess
import com.spectrum.libraries.movie.datasource.remote.apiService.GenresApiService
import com.spectrum.libraries.movie.datasource.remote.dto.toDomain
import com.spectrum.libraries.movie.domain.model.Genres
import com.spectrum.libraries.movie.domain.repository.GenresRepository
import com.spectrum.libraries.persistence.dao.GenreDao
import com.spectrum.libraries.persistence.entity.GenreEntity
import javax.inject.Inject

class GenresRepositoryImpl @Inject constructor(
    private val genresApiService: GenresApiService,
    private val genreDao: GenreDao,
) : GenresRepository {

    private val cachedGenres = mutableMapOf<Int, Genres>()

    override suspend fun getAllGenres(): List<Genres> {
        if (cachedGenres.isEmpty()) loadAndCacheGenres()
        return cachedGenres.values.toList()
    }

    override suspend fun getGenresById(id: Int): Genres? {
        loadAndCacheGenres()
        return cachedGenres[id]
    }

    //Check in-memory cache -> check database -> call API if needed -> save to local
    private suspend fun loadAndCacheGenres() {
        if (cachedGenres.isNotEmpty()) return //Do nothing once the data is available

        val allGenres = mutableListOf<Genres>()
        val persistGenres = genreDao.getAll().map { it.toDomain() }

        if (persistGenres.isEmpty()) {
            genresApiService.getAll()
                .mapSuccess { genres ?: listOf() }
                .mapSuccess { mapNotNull { it.toDomain() } }
                .suspendOnSuccess {
                    allGenres.addAll(data)
                    val entities = data.map { it.toEntity() }
                    genreDao.insert(*entities.toTypedArray())
                }
        } else {
            allGenres.addAll(persistGenres)
        }

        val mapGenres = allGenres.associateBy { it.id }
        cachedGenres.putAll(mapGenres)
    }

    private fun Genres.toEntity(): GenreEntity {
        return GenreEntity(id, name)
    }

    private fun GenreEntity.toDomain(): Genres {
        return Genres(id, name)
    }
}
