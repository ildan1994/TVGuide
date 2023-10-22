package com.example.tvguide.api

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tvguide.TVShowListAdapter
import com.example.tvguide.model.TvShow

@BindingAdapter("list")
fun bindTVShows(recyclerView: RecyclerView, tvShows: List<TvShow>?) {
    val adapter = recyclerView.adapter as TVShowListAdapter
    adapter.submitList(tvShows)
}