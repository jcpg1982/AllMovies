package com.master.machines.allMovies.data.repository

import com.master.machines.allMovies.BuildConfig
import com.master.machines.allMovies.data.models.ResponseAllMoviesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET(BuildConfig.ALL_MOVIES)
    suspend fun getAllMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Response<ResponseAllMoviesDTO>
}