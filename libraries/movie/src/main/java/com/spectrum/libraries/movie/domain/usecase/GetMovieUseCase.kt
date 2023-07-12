package com.spectrum.libraries.movie.domain.usecase

import com.spectrum.libraries.core.usecase.FlowUseCase
import com.spectrum.libraries.core.usecase.UseCaseResult
import com.spectrum.libraries.movie.domain.model.MovieSource
import com.spectrum.libraries.movie.domain.model.PagedMovieList
import com.spectrum.libraries.movie.domain.repository.MovieRepository
import com.spectrum.libraries.movie.domain.usecase.GetMovieUseCase.GetMovieParam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) : FlowUseCase<GetMovieParam, PagedMovieList> {

    override fun execute(param: GetMovieParam): Flow<UseCaseResult<PagedMovieList>> {
        return flow {
            val movieResult = movieRepository.getLiveMovies(param.movieSource, param.page)
            emit(movieResult)
        }
    }

    data class GetMovieParam(
        val movieSource: MovieSource,
        val page: Int,
    )
}
