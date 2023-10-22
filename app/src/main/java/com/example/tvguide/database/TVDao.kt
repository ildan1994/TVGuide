package com.example.tvguide.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tvguide.model.TvShow

@Dao
interface TVDao {
    @Insert
    fun addTVShow(tvShow: TvShow)

    @Insert
    fun addTVShows(tvShows: List<TvShow>)

    @Query("SELECT * FROM tv_show")
    fun loadAllTVShows(): List<TvShow>
}