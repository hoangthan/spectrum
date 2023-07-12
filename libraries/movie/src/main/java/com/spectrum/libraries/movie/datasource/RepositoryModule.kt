package com.spectrum.libraries.movie.datasource

import com.spectrum.libraries.movie.datasource.repositoryImpl.GenresRepositoryImpl
import com.spectrum.libraries.movie.datasource.repositoryImpl.MovieRepositoryImpl
import com.spectrum.libraries.movie.domain.repository.GenresRepository
import com.spectrum.libraries.movie.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepo(impl: MovieRepositoryImpl): MovieRepository

    @Binds
    @Singleton
    abstract fun bindGenresRepo(impl: GenresRepositoryImpl): GenresRepository
}
