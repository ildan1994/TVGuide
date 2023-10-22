package com.example.tvguide.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(
                MoshiConverterFactory.create()
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideTVShowService(retrofit: Retrofit): TVShowService {
        return retrofit.create(TVShowService::class.java)
    }
}