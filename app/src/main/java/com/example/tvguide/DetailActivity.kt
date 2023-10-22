package com.example.tvguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.tvguide.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_POPULARITY ="popularity"
        const val EXTRA_NAME = "name"
        const val EXTRA_FIRST_AIR_DATE = "firstAirDate"
        const val EXTRA_OVERVIEW = "overview"
        const val EXTRA_POSTER = "poster"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w185/"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extras = intent.extras
        with(binding) {
            nameText.text = extras?.getString(EXTRA_NAME).orEmpty()
            popularityText.text = extras?.getString(EXTRA_POPULARITY).orEmpty()
            firstAirDateText.text = extras?.getString(EXTRA_FIRST_AIR_DATE).orEmpty()
            overviewText.text =
                getString(R.string.movie_overview, extras?.getString(EXTRA_OVERVIEW).orEmpty())
            val posterPath = extras?.getString(EXTRA_POSTER).orEmpty()
            Glide.with(this@DetailActivity)
                .load("$IMAGE_URL$posterPath")
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(tvShowPoster)
        }


    }
}