package com.example.tvguide.model

data class TvShowResponse(
    val page: Int,
    val results: List<TvShow>,
)