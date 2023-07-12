package com.spectrum.features.movie.ui.screens.movieSearch

import com.spectrum.features.movie.ui.screens.common.AbstractMoviePagingSource
import com.spectrum.libraries.core.usecase.UseCaseResult
import com.spectrum.libraries.movie.domain.usecase.SearchMovieUseCase
import com.spectrum.libraries.movie.domain.usecase.model.PagedMovieList
import kotlinx.coroutines.flow.first

class SearchMoviePagingSource constructor(
    private val name: String,
    private val searchMovieUseCase: SearchMovieUseCase,
) : AbstractMoviePagingSource() {

    override suspend fun loadData(params: LoadParams<Int>): UseCaseResult<PagedMovieList> {
        val pageNumber = params.key ?: 1
        val useCaseParam = SearchMovieUseCase.SearchMovieParam(name, pageNumber)
        return searchMovieUseCase.execute(useCaseParam).first()
    }
}
