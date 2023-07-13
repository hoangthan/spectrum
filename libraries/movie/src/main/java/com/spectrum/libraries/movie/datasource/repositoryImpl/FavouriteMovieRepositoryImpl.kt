package com.spectrum.libraries.movie.datasource.repositoryImpl

import com.spectrum.libraries.movie.domain.model.Movie
import com.spectrum.libraries.movie.domain.repository.FavouriteMovieRepository
import com.spectrum.libraries.persistence.dao.FavouriteMovieDao
import com.spectrum.libraries.persistence.entity.FavouriteMovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouriteMovieRepositoryImpl @Inject constructor(
    private val favMovieDao: FavouriteMovieDao
) : FavouriteMovieRepository {

    override suspend fun save(movie: Movie) {
        favMovieDao.insert(movie.toEntity())
    }

    override suspend fun delete(movie: Movie) {
        favMovieDao.delete(movie.id)
    }

    override fun getAll(): Flow<List<Movie>> {
        return favMovieDao.getAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getByIds(vararg ids: Long): Flow<List<Movie>> {
        val idArray = ids.toTypedArray().toLongArray()
        return favMovieDao.loadByIds(idArray).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    private fun Movie.toEntity(): FavouriteMovieEntity {
        return FavouriteMovieEntity(
            id = id,
            adult = adult,
            backdropPath = backdropPath,
            genresIds = genresIds,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount,
        )
    }

    private fun FavouriteMovieEntity.toDomain(): Movie {
        return Movie(
            id = id,
            adult = adult,
            backdropPath = backdropPath,
            genresIds = genresIds,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount,
            genres = emptyList()
        )
    }
}
