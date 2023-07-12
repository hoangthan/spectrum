package com.spectrum.libraries.movie.datasource.remote.apiService

import com.skydoves.sandwich.ApiResponse
import com.spectrum.libraries.movie.datasource.remote.dto.GetGenresResponse
import retrofit2.http.GET


interface GenresApiService {
    @GET("genre/movie/list")
    suspend fun getAll(): ApiResponse<GetGenresResponse>
}
