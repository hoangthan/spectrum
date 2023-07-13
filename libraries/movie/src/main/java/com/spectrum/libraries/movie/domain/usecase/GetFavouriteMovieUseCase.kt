package com.spectrum.libraries.movie.domain.usecase

import com.spectrum.libraries.core.usecase.FlowUseCase
import com.spectrum.libraries.core.usecase.UseCaseResult
import com.spectrum.libraries.core.usecase.asSuccessResult
import com.spectrum.libraries.movie.domain.model.Movie
import com.spectrum.libraries.movie.domain.repository.FavouriteMovieRepository
import com.spectrum.libraries.movie.domain.usecase.GetFavouriteMovieUseCase.QueryType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFavouriteMovieUseCase @Inject constructor(
    private val favMovieRepository: FavouriteMovieRepository
) : FlowUseCase<QueryType, List<Movie>> {

    override fun execute(param: QueryType): Flow<UseCaseResult<List<Movie>>> {
        val flow = when (param) {
            is QueryType.All -> favMovieRepository.getAll()
            is QueryType.Specific -> favMovieRepository.getByIds(param.ids)
        }

        return flow
            .map { it.asSuccessResult() }
            .catch { emit(UseCaseResult.Failure(it)) }
    }

    sealed interface QueryType {
        object All : QueryType
        data class Specific(var ids: Long) : QueryType
    }
}
