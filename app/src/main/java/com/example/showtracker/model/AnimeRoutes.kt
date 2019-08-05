package com.example.showtracker.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeRoutes {
    @GET("season/2018/summer")
    fun getSeasonalAnimes(): Single<SeasonalData>

    @GET("anime/{id}/recommendations")
    fun getSimilarAnime(@Path("id") id: Int): Single<AnimeSimilarModel>

    @GET("anime/{id}/characters_staff")
    fun getAnimeCharacters(@Path("id") id: Int): Single<AnimeCharacterModel>

    @GET("anime/{id}")
    fun getAnimeDetails(@Path("id") id: Int): Single<AnimeDetailModel>
}
