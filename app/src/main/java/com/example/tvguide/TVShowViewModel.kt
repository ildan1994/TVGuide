package com.example.tvguide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvguide.model.TvShow
import com.example.tvguide.repository.TVShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowViewModel @Inject constructor(
    private var tvShowRepository: TVShowRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    private val _tvShows = MutableStateFlow<List<TvShow>>(emptyList())
    val tvShows: StateFlow<List<TvShow>> = _tvShows

    private val _error = MutableStateFlow<String>("")
    val error: StateFlow<String> = _error

    init {
        fetchTvShow()
    }

    private fun fetchTvShow() {
        viewModelScope.launch(dispatcher) {
            tvShowRepository.fetchTVShow().catch { cause ->
                _error.value = "An exception occurred: ${cause.message}"
            }.collect { tvShows ->
                _tvShows.value = tvShows
            }
        }
    }
}