package com.spectrum.libraries.movie.domain.usecase

import com.spectrum.libraries.core.usecase.FlowUseCase
import com.spectrum.libraries.core.usecase.UseCaseResult
import com.spectrum.libraries.movie.domain.model.MovieDetails
import com.spectrum.libraries.movie.domain.repository.MovieRepository
import com.spectrum.libraries.movie.domain.usecase.GetMovieDetailsUseCase.GetMovieDetailsParam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) : FlowUseCase<GetMovieDetailsParam, MovieDetails> {

    override fun execute(param: GetMovieDetailsParam): Flow<UseCaseResult<MovieDetails>> {
        return flow {
            val result = movieRepository.getMovieDetails(param.id)
            emit(result)
        }
    }

    data class GetMovieDetailsParam(
        val id: Long
    )
}
