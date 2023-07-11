package com.spectrum.libraries.movie.datasource.remote

import com.skydoves.sandwich.ApiResponse
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
}
