package com.example.tvguide.repository

import com.example.tvguide.api.TVShowService
import com.example.tvguide.database.TVDao
import com.example.tvguide.database.TVDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun providesRepository(tvShowService: TVShowService, tvDao: TVDao): TVShowRepository {
        return TVShowRepositoryImpl(tvShowService,tvDao)
    }
    @Singleton
    @Provides
    fun provideDispatcher() : CoroutineDispatcher = Dispatchers.Main

}