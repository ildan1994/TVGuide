package com.example.tvguide.api

import com.example.tvguide.model.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TVShowService {
    @GET("tv/on_the_air")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): TvShowResponse
}