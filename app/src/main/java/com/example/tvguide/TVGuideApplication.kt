package com.example.tvguide

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.tvguide.repository.TVShowRepository
import com.example.tvguide.repository.TVShowRepositoryImpl
import com.example.tvguide.api.TVShowService
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltAndroidApp
class TVGuideApplication: Application(), Configuration.Provider {
    @Inject lateinit var workerFactory: HiltWorkerFactory
    override fun onCreate() {
        super.onCreate()
        val constraints =
            Constraints.Builder().setRequiredNetworkType(
                NetworkType.CONNECTED
            ).build()

        val workerRequest = PeriodicWorkRequest
            .Builder(TVShowWorker::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .addTag("tvShow-work")
            .build()
        WorkManager.getInstance(applicationContext)
            .enqueue(workerRequest)
    }

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}