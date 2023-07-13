package com.spectrum.libraries.movie.domain.usecase

import com.spectrum.libraries.core.usecase.SuspendUseCase
import com.spectrum.libraries.core.usecase.UseCaseResult
import com.spectrum.libraries.core.usecase.asSuccessResult
import com.spectrum.libraries.movie.domain.model.Movie
import com.spectrum.libraries.movie.domain.repository.FavouriteMovieRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoveFavouriteMovieUseCase @Inject constructor(
    private val favMovieRepository: FavouriteMovieRepository
) : SuspendUseCase<Movie, Boolean> {

    override suspend fun execute(param: Movie): UseCaseResult<Boolean> {
        return try {
            favMovieRepository.delete(param)
            true.asSuccessResult()
        } catch (exception: Exception) {
            false.asSuccessResult()
        }
    }
}
