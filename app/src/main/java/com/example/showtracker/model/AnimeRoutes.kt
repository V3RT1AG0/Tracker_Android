package com.example.showtracker.model

import io.reactivex.Single
import retrofit2.http.GET

interface AnimeRoutes {
    @GET("season/2019/summer")
    fun getSeasonalAnimes(): Single<SeasonalData>
}
