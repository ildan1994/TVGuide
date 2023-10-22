package com.example.tvguide.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tvguide.model.TvShow


@Database(entities = [TvShow::class], version = 1, exportSchema = false)
abstract class TVDatabase : RoomDatabase() {
    abstract fun tvDao():TVDao
    companion object {
        @Volatile
        private var instance: TVDatabase? = null
        fun getInstance(context: Context):
                TVDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(
                    context
                ).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context):
                TVDatabase {
            return Room.databaseBuilder(
                context,
                TVDatabase::class.java, "movie-db"
            )
                .build()
        }
    }
}