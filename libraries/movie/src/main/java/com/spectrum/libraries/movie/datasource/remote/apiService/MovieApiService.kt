package com.spectrum.libraries.movie.datasource.remote.apiService

import com.skydoves.sandwich.ApiResponse
import com.spectrum.libraries.movie.datasource.remote.dto.GetMovieResponseDto
import com.spectrum.libraries.movie.datasource.remote.dto.MovieDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApiService {
    @GET("movie/{type}")
    suspend fun getMovies(
        @Path("type") movieType: String,
        @Query("page") page: Int = 1,
    ): ApiResponse<GetMovieResponseDto>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
    ): ApiResponse<GetMovieResponseDto>

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") id: Long): ApiResponse<MovieDetailsDto>
}
