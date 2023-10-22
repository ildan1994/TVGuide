package com.example.tvguide.repository

import com.example.tvguide.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TVShowRepository {
    suspend fun fetchTVShow(): Flow<List<TvShow>>
    suspend fun fetchTVShowsFromNetwork()
}
