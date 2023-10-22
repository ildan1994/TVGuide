package com.example.tvguide.repository

import android.util.Log
import com.example.tvguide.api.TVShowService
import com.example.tvguide.database.TVDao
import com.example.tvguide.database.TVDatabase
import com.example.tvguide.model.TvShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit

class TVShowRepositoryImpl(
    private val tvShowService: TVShowService,
    private val tvDao: TVDao,
) : TVShowRepository {
    private val apiKey = "687de03fe9c25a9c2f8047373e3367fc"

    override suspend fun fetchTVShow(): Flow<List<TvShow>> {
        return flow {
            val tvShowsLocal = tvDao.loadAllTVShows()
            if (tvShowsLocal.isNotEmpty()) {
                emit(tvShowsLocal)
            } else {
                emit(
                    tvShowService.getPopularMovies(apiKey).results
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun fetchTVShowsFromNetwork() {
        try {
            val tvShows = tvShowService.getPopularMovies(apiKey).results
            tvDao.addTVShows(tvShows)
        }  catch (exception: Exception) {
            Log.d("TVShowRepositoryImpl", "An error occurred:${exception.message}")
        }
    }
}