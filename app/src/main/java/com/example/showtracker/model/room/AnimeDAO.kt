package com.example.showtracker.model.room



import androidx.room.*
import com.example.showtracker.model.Anime
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface AnimeDAO {

    @Query("SELECT * from AnimeData")
    fun getAll(): Single<List<Anime>>

    @Insert
    fun insert(anime: Anime):Completable

    @Update
    fun update(anime: Anime)

    @Delete
    fun delete(anime: Anime):Completable
}