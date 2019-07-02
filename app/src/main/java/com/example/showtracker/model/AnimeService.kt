package com.example.showtracker.model
import android.util.Log
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class AnimeService {

    @Inject
    lateinit var anime_routes: AnimeRoutes

    init {/*
        anime_routes = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(AnimeRoutes::class.java)*/
        DaggerApiComponent.create().inject(this)

    }

    fun getAnimeList():Single<SeasonalData>{
        return anime_routes.getSeasonalAnimes()
    }
}