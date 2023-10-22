package com.example.tvguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tvguide.databinding.ActivityMainBinding
import com.example.tvguide.model.TvShow
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TVShowViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        setContentView(binding.root)
        val adapter = TVShowListAdapter(layoutInflater) { tvShow ->
            openDetailActivity(tvShow)
        }

        with(binding) {
            tvShowList.adapter = adapter
        }
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                launch {
//                    viewModel.tvShows.collect { tvShows ->
//                        adapter.submitList(tvShows)
//                    }
//                }
                launch {
                    viewModel.error.collect { error ->
                        if (error.isNotEmpty()) {
                            Snackbar.make(binding.tvShowList, error, Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun openDetailActivity(tvShow: TvShow) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_NAME, tvShow.name)
            putExtra(DetailActivity.EXTRA_FIRST_AIR_DATE, tvShow.firstAirDate)
            putExtra(DetailActivity.EXTRA_OVERVIEW, tvShow.overview)
            putExtra(DetailActivity.EXTRA_POSTER, tvShow.posterPath)
            putExtra(DetailActivity.EXTRA_POPULARITY, tvShow.popularity.toString())
        }
        startActivity(intent)
    }

}