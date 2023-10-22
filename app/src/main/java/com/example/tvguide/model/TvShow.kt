package com.example.tvguide.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity(tableName = "tv_show")
data class TvShow(
//    val backdrop_path: String,
//    val genre_ids: List<Int>,
    @PrimaryKey
    val id: Int,
//    val origin_country: List<String>,
//    val original_language: String,
//    val original_name: String,
//    val vote_average: Double,
//    val vote_count: Int
    @field:Json(name = "first_air_date")
    val firstAirDate: String,
    val name: String,
    val overview: String,
    val popularity: Double,
    @field:Json(name = "poster_path")
    val posterPath: String,
)