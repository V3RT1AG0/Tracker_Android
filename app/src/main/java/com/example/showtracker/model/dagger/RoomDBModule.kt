package com.example.showtracker.model.dagger

import android.app.Application
import android.content.Context
import com.example.showtracker.model.room.MyDatabase
import dagger.Module
import dagger.Provides
import androidx.room.Room
import com.example.showtracker.model.room.AnimeDAO
import javax.inject.Singleton


@Module
class RoomDBModule(val myContext: Context) {

    /** @Singleton in Dagger 2 are thread safe with double checked locking. Hence we can replace the code in MyDatabase
    with @Singleton annotaion **/


    @Singleton
    @Provides
    fun getAnimeDAO(db: MyDatabase):AnimeDAO {
        return db.getAnimeDAO()
    }

    @Provides
    @Singleton
    fun getDatabase(context: Context): MyDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MyDatabase::class.java, MyDatabase.DATABASE_NAME
        )
            .build()
    }

    @Provides
    @Singleton
    fun getContext():Context{
        return myContext
    }
}