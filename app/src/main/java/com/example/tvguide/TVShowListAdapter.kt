package com.example.tvguide

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvguide.model.TvShow
import com.example.tvguide.databinding.ViewTvShowItemBinding

class TVShowListAdapter(
    private val layoutInflater: LayoutInflater,
    private val onClick: (TvShow) -> Unit,
) : ListAdapter<TvShow, TVShowListAdapter.TVShowViewHolder>(
    object : DiffUtil.ItemCallback<TvShow>() {
        override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem.name == newItem.name
        }
    }
) {
    class TVShowViewHolder(private val binding: ViewTvShowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        fun bindData(tvShow: TvShow) {
            binding.tvShowTitle.text = tvShow.name
            Glide.with(binding.root.context)
                .load("$imageUrl${tvShow.posterPath}")
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(binding.tvShowPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        val binding = ViewTvShowItemBinding.inflate(layoutInflater, parent, false)
        return TVShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindData(item)
        holder.itemView.setOnClickListener{
            onClick(item)
        }
    }
}