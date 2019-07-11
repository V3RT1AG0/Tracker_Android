package com.example.showtracker.model.room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.showtracker.model.Anime


@Database(entities = arrayOf(Anime::class), version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getAnimeDAO(): AnimeDAO

    companion object {
        private var instance: MyDatabase? = null
        fun getInstance(context: Context): MyDatabase? {
            if (instance == null)
                synchronized(MyDatabase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java, "ShowTracker.db"
                    )
                        .build()
                }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

    }

}