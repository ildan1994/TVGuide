package com.example.tvguide

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.tvguide.repository.TVShowRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltWorker
class TVShowWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
    private val tvShowRepository: TVShowRepository
) : Worker(context, params) {
    override fun doWork(): Result {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowRepository.fetchTVShowsFromNetwork()
        }
        return Result.success()
    }
}