package com.spectrum.libraries.movie.datasource.repositoryImpl

import com.skydoves.sandwich.mapSuccess
import com.spectrum.libraries.core.usecase.UseCaseResult
import com.spectrum.libraries.movie.datasource.remote.apiService.MovieApiService
import com.spectrum.libraries.movie.datasource.remote.dto.toDomain
import com.spectrum.libraries.movie.datasource.remote.dto.toPagedMoveList
import com.spectrum.libraries.movie.domain.model.MovieDetails
import com.spectrum.libraries.movie.domain.model.MovieSource
import com.spectrum.libraries.movie.domain.model.PagedMovieList
import com.spectrum.libraries.movie.domain.repository.MovieRepository
import com.spectrum.libraries.network.utils.toUseCaseResult
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
) : MovieRepository {

    override suspend fun getLiveMovies(
        type: MovieSource,
        page: Int
    ): UseCaseResult<PagedMovieList> {
        val sourcePath = SourcePath.fromMovieType(type)
        return movieApiService.getMovies(sourcePath.path, page)
            .mapSuccess { this.toPagedMoveList() }
            .toUseCaseResult()
    }

    override suspend fun searchMovie(name: String, page: Int): UseCaseResult<PagedMovieList> {
        return movieApiService.searchMovie(name, page)
            .mapSuccess { this.toPagedMoveList() }
            .toUseCaseResult()
    }

    override suspend fun getMovieDetails(id: Long): UseCaseResult<MovieDetails> {
        return movieApiService.getMovieDetails(id)
            .mapSuccess { toDomain() }
            .toUseCaseResult()
    }

    private sealed class SourcePath(val path: String) {
        object Upcoming : SourcePath("upcoming")
        object Popular : SourcePath("popular")
        object TopRated : SourcePath("top_rated")
        object NowPlaying : SourcePath("now_playing")

        companion object {
            fun fromMovieType(type: MovieSource): SourcePath {
                return when (type) {
                    MovieSource.Popular -> Popular
                    MovieSource.TopRated -> TopRated
                    MovieSource.Upcoming -> Upcoming
                    MovieSource.NowPlaying -> NowPlaying
                }
            }
        }
    }
}
