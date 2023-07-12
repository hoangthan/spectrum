package com.spectrum.libraries.movie.domain.usecase

import com.spectrum.libraries.core.usecase.FlowUseCase
import com.spectrum.libraries.core.usecase.UseCaseResult
import com.spectrum.libraries.movie.domain.repository.MovieRepository
import com.spectrum.libraries.movie.domain.model.PagedMovieList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : FlowUseCase<SearchMovieUseCase.SearchMovieParam, PagedMovieList> {

    override fun execute(param: SearchMovieParam): Flow<UseCaseResult<PagedMovieList>> {
        return flow {
            val result = movieRepository.searchMovie(param.keyword, param.page)
            emit(result)
        }
    }

    data class SearchMovieParam(
        val keyword: String,
        val page: Int,
    )
}
