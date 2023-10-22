package com.example.tvguide.database

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TVDatabase =
        TVDatabase.getInstance(context)

    @Provides
    fun provideDao(tvDatabase: TVDatabase): TVDao = tvDatabase.tvDao()
}