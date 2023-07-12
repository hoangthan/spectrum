package com.spectrum.features.movie.ui.screens.movieList

import com.spectrum.features.movie.ui.screens.common.AbstractMoviePagingSource
import com.spectrum.libraries.core.usecase.UseCaseResult
import com.spectrum.libraries.movie.domain.usecase.GetMovieUseCase
import com.spectrum.libraries.movie.domain.usecase.GetMovieUseCase.GetMovieParam
import com.spectrum.libraries.movie.domain.model.MovieSource
import com.spectrum.libraries.movie.domain.model.PagedMovieList
import kotlinx.coroutines.flow.first

class MovieListPagingSource constructor(
    private val movieSource: MovieSource,
    private val getMovieUseCase: GetMovieUseCase,
) : AbstractMoviePagingSource() {

    override suspend fun loadData(params: LoadParams<Int>): UseCaseResult<PagedMovieList> {
        val pageNumber = params.key ?: 1
        val useCaseParam = GetMovieParam(movieSource, pageNumber)
        return getMovieUseCase.execute(useCaseParam).first()
    }
}
