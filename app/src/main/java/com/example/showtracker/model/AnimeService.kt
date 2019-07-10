package com.example.showtracker.model

import com.example.showtracker.model.dagger.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject


class AnimeService @Inject constructor(val anime_routes: AnimeRoutes) {

    fun getAnimeList(): Single<SeasonalData> {
        return anime_routes.getSeasonalAnimes()
    }

    fun getCharacters(id: Int): Single<AnimeCharacterModel> {
        return anime_routes.getAnimeCharacters(id)
    }

    fun getSimilar(id: Int): Single<AnimeSimilarModel> {
        return anime_routes.getSimilarAnime(id)
    }
}