package com.spectrum.libraries.movie.datasource.remote

import com.spectrum.libraries.movie.BuildConfig
import com.spectrum.libraries.movie.datasource.remote.apiService.GenresApiService
import com.spectrum.libraries.movie.datasource.remote.apiService.MovieApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    @Singleton
    fun provideMovieApiService(
        okHttpClient: OkHttpClient,
        callAdapterFactory: CallAdapter.Factory,
        converterFactory: Converter.Factory
    ): MovieApiService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(MovieApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGenresApiService(
        okHttpClient: OkHttpClient,
        callAdapterFactory: CallAdapter.Factory,
        converterFactory: Converter.Factory
    ): GenresApiService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(GenresApiService::class.java)
    }
}
