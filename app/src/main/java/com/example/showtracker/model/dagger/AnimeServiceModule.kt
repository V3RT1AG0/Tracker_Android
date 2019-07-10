package com.example.showtracker.model.dagger

import com.example.showtracker.model.AnimeRoutes
import com.example.showtracker.model.AnimeService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AnimeServiceModule{
    private val BASE_URL = "https://api.jikan.moe/v3/"

    @Provides
    fun getAnimeApi(): AnimeRoutes {
        val anime_routes = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(AnimeRoutes::class.java)
        return anime_routes
    }

    @Provides
    fun getAnimeService(route:AnimeRoutes): AnimeService {
        return AnimeService(route)
    }

}
