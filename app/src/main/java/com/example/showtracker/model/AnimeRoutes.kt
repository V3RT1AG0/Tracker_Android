package com.example.showtracker.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeRoutes {
    @GET("season/2019/summer")
    fun getSeasonalAnimes(): Single<SeasonalData>

    @GET("anime/{id}/recommendations")
    fun getSimilarAnime(@Path("id") id: Int): Single<AnimeSimilarModel>

    @GET("anime/{id}/recommendations")
    fun getAnimeCharacters(@Path("id") id: Int): Single<AnimeCharacterModel>
}
