package com.example.showtracker.model
import com.example.showtracker.model.dagger.DaggerApiComponent
import io.reactivex.Single
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